package com.dikshanta.food.delivery.foodDeliveryBackend.listeners;

import com.dikshanta.food.delivery.foodDeliveryBackend.events.PasswordResetEvent;
import com.dikshanta.food.delivery.foodDeliveryBackend.services.EmailService;
import com.dikshanta.food.delivery.foodDeliveryBackend.utils.Html;
import com.dikshanta.food.delivery.foodDeliveryBackend.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordResetEventListener {
    private final EmailService emailService;
    private final Utils utils;


    @EventListener
    @Async
    public void handlePasswordResetEvent(PasswordResetEvent passwordResetEvent) {
        emailService.sendEmail(passwordResetEvent.getEmail(), utils.getAdmin().getEmail(), "Password reset successful", Html.getPasswordResetConfirmation(passwordResetEvent.getName()));

    }
}
