package com.dikshanta.food.delivery.foodDeliveryBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class FoodDeliveryBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(FoodDeliveryBackendApplication.class, args);
    }

}
