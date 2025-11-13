package com.dikshanta.food.delivery.foodDeliveryBackend.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionResponse {
    private LocalDateTime exceptionTime;
    private String exceptionName;
    private String detailedMessage;
    private Map<String, String> customValidators;
}
