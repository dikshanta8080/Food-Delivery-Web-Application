package com.dikshanta.food.delivery.foodDeliveryBackend.exceptions;

public class TokenDoesNotExistsException extends RuntimeException {
    public TokenDoesNotExistsException(String message) {
        super(message);
    }
}
