package com.dikshanta.food.delivery.foodDeliveryBackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String openingTime;
    private String closingTime;
    private boolean openStatus;
    private String imageUrl;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToMany(mappedBy = "restaurant")
    private List<FoodItem> foodItems;
    @OneToMany(mappedBy = "restaurant")
    private List<Category> categories;
}
