package com.dikshanta.food.delivery.foodDeliveryBackend.dtos;

import lombok.*;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeocodeCoordinates {
    private Double latitude;
    private Double longitude;
}
