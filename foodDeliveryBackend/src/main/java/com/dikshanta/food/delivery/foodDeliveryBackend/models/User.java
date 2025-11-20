package com.dikshanta.food.delivery.foodDeliveryBackend.models;

import com.dikshanta.food.delivery.foodDeliveryBackend.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@Table(name = "\"user\"")
public class User extends DateAuditable {

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "user",
            fetch = FetchType.LAZY
    )
    private List<Address> addresses;

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

    public User() {
        this.addresses = new ArrayList<>();
    }

    public void addAddress(Address address) {
        if (this.addresses == null) {
            this.addresses = new ArrayList<>();
        }
        this.addresses.add(address);
        if (address != null) {
            address.setUser(this);
        }
    }

    public void removeAddress(Address address) {
        if (this.addresses != null) {
            this.addresses.remove(address);
        }
        if (address != null) {
            address.setUser(null);
        }
    }
}