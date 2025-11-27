package com.dikshanta.food.delivery.foodDeliveryBackend.exceptions;

public class OtpExpiredException extends RuntimeException {
    public OtpExpiredException(String message) {
        super(message);
    }
}
