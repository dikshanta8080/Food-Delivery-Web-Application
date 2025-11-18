package com.dikshanta.food.delivery.foodDeliveryBackend.models;

import com.dikshanta.food.delivery.foodDeliveryBackend.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "\"user\"")
public class User extends Auditable {
    @Column(nullable = false)
    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "user",
            fetch = FetchType.LAZY)
    private final List<Address> addresses = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, length = 70)
    private String password;
    @Column(nullable = true)
    private String profileImageUrl;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public void addAddress(Address address) {
        addresses.add(address);
        address.setUser(this);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setUser(null);

    }
}
