package com.dikshanta.food.delivery.foodDeliveryBackend.dtos;

import com.dikshanta.food.delivery.foodDeliveryBackend.validators.EmailValidator;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDoo {
    @EmailValidator
    private String email;
    @NotBlank(message = "Please provide non blank and valid password")
    private String password;
}
