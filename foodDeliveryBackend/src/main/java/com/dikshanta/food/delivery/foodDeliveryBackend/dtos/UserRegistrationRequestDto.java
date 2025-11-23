package com.dikshanta.food.delivery.foodDeliveryBackend.dtos;

import com.dikshanta.food.delivery.foodDeliveryBackend.validators.EmailValidator;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserRegistrationRequestDto {
    @NotBlank(message = "Please provide non blank and valid name")
    private String name;
    @EmailValidator
    private String email;
    @NotBlank(message = "Please provide non blank and valid password")
    private String password;

}
