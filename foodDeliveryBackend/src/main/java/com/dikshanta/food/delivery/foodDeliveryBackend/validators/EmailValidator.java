package com.dikshanta.food.delivery.foodDeliveryBackend.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = EmailValidatorConstraint.class)
public @interface EmailValidator {
    String message() default "Please provide valid email";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
