package com.dikshanta.food.delivery.foodDeliveryBackend.listeners;

import com.dikshanta.food.delivery.foodDeliveryBackend.events.UserResistrationEvent;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.User;
import com.dikshanta.food.delivery.foodDeliveryBackend.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationListener {
    private final EmailService emailService;

    @Autowired
    public UserRegistrationListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @EventListener
    @Async
    public void handlecustomerRegistration(UserResistrationEvent event) {
        User user = event.getUser();
        emailService.sendEmail("haha", "haha", "haha", "haha");


    }
}
