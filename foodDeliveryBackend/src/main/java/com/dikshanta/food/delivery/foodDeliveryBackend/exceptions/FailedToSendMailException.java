package com.dikshanta.food.delivery.foodDeliveryBackend.exceptions;

public class FailedToSendMailException extends RuntimeException {
    public FailedToSendMailException(String message) {
        super(message);
    }
}
