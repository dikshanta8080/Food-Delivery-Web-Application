package com.dikshanta.food.delivery.foodDeliveryBackend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/restaurant")
public class RestaurantManagementController {
    @GetMapping("/greet")
    public ResponseEntity<String> greetRestaurant() {
        return new ResponseEntity<>("Hello manager", HttpStatus.OK);
    }
}
