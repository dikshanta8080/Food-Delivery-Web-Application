package com.dikshanta.food.delivery.foodDeliveryBackend.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenRefreshRequestDto {
    @NotBlank(message = "Please provide valid token")
    private String token;
}
