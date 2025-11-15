package com.dikshanta.food.delivery.foodDeliveryBackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"userAddress\"")
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    private double latitude;
    private double longitude;
    private String fullAddress;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "province_id", referencedColumnName = "id")
    private Province province;
    @ManyToOne
    @JoinColumn(name = "district_id", referencedColumnName = "id")
    private District district;
    @ManyToOne
    @JoinColumn(name = "municipality_id", referencedColumnName = "id")
    private Municipality municipality;
}
