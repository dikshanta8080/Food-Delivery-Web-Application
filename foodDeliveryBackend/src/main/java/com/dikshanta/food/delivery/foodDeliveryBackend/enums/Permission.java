package com.dikshanta.food.delivery.foodDeliveryBackend.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    CUSTOMER_CREATE("customer:create"),
    CUSTOMER_READ("customer:read"),
    CUSTOMER_UPDATE("customer:update"),
    CUSTOMER_DELETE("customer:delete"),

    RESTAURANT_CREATE("restaurant:create"),
    RESTAURANT_READ("restaurant:read"),
    RESTAURANT_UPDATE("restaurant:update"),
    RESTAURANT_DELETE("restaurant:delete"),

    ADMIN_CREATE("admin:create"),
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete");

    @Getter
    private final String permissionName;
}
