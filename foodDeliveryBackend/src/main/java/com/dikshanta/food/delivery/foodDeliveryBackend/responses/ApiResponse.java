package com.dikshanta.food.delivery.foodDeliveryBackend.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    private boolean status;
    private HttpStatus httpStatus;
    private String message;
    private T responseObject;
}
