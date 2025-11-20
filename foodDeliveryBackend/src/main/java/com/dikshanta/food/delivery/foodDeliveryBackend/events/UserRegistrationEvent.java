package com.dikshanta.food.delivery.foodDeliveryBackend.events;

import com.dikshanta.food.delivery.foodDeliveryBackend.models.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserRegistrationEvent extends ApplicationEvent {
    private User user;

    public UserRegistrationEvent(Object source, User user) {
        super(source);
    }
}
