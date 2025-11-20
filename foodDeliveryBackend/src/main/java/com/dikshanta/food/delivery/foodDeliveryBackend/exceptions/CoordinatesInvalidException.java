package com.dikshanta.food.delivery.foodDeliveryBackend.exceptions;

public class CoordinatesInvalidException extends RuntimeException {
    public CoordinatesInvalidException(String message, Exception e) {
        super(message);
    }
}
