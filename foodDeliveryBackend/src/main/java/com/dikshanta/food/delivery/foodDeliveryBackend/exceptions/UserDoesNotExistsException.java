package com.dikshanta.food.delivery.foodDeliveryBackend.exceptions;

public class UserDoesNotExistsException extends RuntimeException {
    public UserDoesNotExistsException(String message) {
        super(message);
    }
}
