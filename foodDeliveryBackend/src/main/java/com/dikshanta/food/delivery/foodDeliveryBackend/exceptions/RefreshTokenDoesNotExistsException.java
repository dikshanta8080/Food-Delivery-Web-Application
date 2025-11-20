package com.dikshanta.food.delivery.foodDeliveryBackend.exceptions;

public class RefreshTokenDoesNotExistsException extends RuntimeException {
    public RefreshTokenDoesNotExistsException(String message) {
        super(message);
    }
}
