package com.dikshanta.food.delivery.foodDeliveryBackend.events;

import com.dikshanta.food.delivery.foodDeliveryBackend.models.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

@Component
@Getter
public class UserResistrationEvent extends ApplicationEvent {
    private User user;

    public UserResistrationEvent(Object source, User user) {
        super(source);
    }
}
