package com.dikshanta.food.delivery.foodDeliveryBackend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddDeliveryRequestDto {
    @NotNull(message = "Please provide valid userId")
    private Long userId;
    @NotNull(message = "Please provide valid province id")
    private Long provinceId;
    @NotNull(message = "Please provide valid district id")
    private Long districtId;
    @NotNull(message = "Please provide valid municipality id")
    private Long municipalityId;
    @NotBlank(message = "Please provide valid detailed address")
    private String detailedAddress;
}
