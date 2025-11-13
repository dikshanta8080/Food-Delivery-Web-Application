package com.dikshanta.food.delivery.foodDeliveryBackend.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.dikshanta.food.delivery.foodDeliveryBackend.enums.Permission.*;

@RequiredArgsConstructor
public enum Role {
    CUSTOMER(Set.of(
            CUSTOMER_CREATE,
            CUSTOMER_READ,
            CUSTOMER_UPDATE,
            CUSTOMER_DELETE)),

    RESTAURANT(Set.of(
            Permission.RESTAURANT_CREATE,
            Permission.RESTAURANT_READ,
            Permission.RESTAURANT_UPDATE,
            Permission.RESTAURANT_DELETE
    )),
    ADMIN(Set.of(
            Permission.CUSTOMER_CREATE,
            Permission.CUSTOMER_READ,
            Permission.CUSTOMER_UPDATE,
            Permission.CUSTOMER_DELETE,
            Permission.RESTAURANT_CREATE,
            Permission.RESTAURANT_READ,
            Permission.RESTAURANT_UPDATE,
            Permission.RESTAURANT_DELETE,
            Permission.ADMIN_CREATE,
            Permission.ADMIN_READ,
            Permission.ADMIN_UPDATE,
            Permission.ADMIN_DELETE
    ));
    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermissionName()))
                .toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
