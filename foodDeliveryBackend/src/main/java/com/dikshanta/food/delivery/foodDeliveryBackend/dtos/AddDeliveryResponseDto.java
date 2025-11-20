package com.dikshanta.food.delivery.foodDeliveryBackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddDeliveryResponseDto {
    private String province;
    private String district;
    private String municipality;
    private String fullAddress;
    private String detailedAddress;
}
