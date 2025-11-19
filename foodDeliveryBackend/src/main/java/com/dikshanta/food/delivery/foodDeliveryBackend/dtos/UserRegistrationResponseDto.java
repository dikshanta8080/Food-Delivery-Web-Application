package com.dikshanta.food.delivery.foodDeliveryBackend.dtos;

import com.dikshanta.food.delivery.foodDeliveryBackend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegistrationResponseDto {
    private String name;
    private String email;
    private Role role;
}
