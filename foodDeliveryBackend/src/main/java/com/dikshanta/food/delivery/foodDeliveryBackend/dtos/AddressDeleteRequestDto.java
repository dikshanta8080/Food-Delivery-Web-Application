package com.dikshanta.food.delivery.foodDeliveryBackend.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDeleteRequestDto {
    @NotNull(message = "Please provide valid address id")
    private Long addressId;
}
