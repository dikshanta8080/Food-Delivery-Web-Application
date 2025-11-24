package com.dikshanta.food.delivery.foodDeliveryBackend.exceptions;

public class AddressNotFoundException extends RuntimeException {
  public AddressNotFoundException(String message) {
    super(message);
  }
}
