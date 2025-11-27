package com.dikshanta.food.delivery.foodDeliveryBackend.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;


@Getter
public class PasswordResetEvent extends ApplicationEvent {
    private final String email;
    private final String name;

    public PasswordResetEvent(Object source, String email, String name) {
        super(source);
        this.email = email;
        this.name = name;
    }
}
