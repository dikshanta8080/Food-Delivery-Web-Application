package com.dikshanta.food.delivery.foodDeliveryBackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordResetRequestDto {
    private String email;
    private String otp;
    private String newPassword;
}
