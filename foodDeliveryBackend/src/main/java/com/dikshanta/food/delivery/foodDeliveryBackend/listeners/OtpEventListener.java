package com.dikshanta.food.delivery.foodDeliveryBackend.listeners;

import com.dikshanta.food.delivery.foodDeliveryBackend.events.OtpGeneratedEvent;
import com.dikshanta.food.delivery.foodDeliveryBackend.services.EmailService;
import com.dikshanta.food.delivery.foodDeliveryBackend.utils.Html;
import com.dikshanta.food.delivery.foodDeliveryBackend.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OtpEventListener {
    private final EmailService emailService;
    private final Utils utils;

    @EventListener
    @Async
    public void handleOtpEvent(OtpGeneratedEvent otpGeneratedEvent) {
        emailService.sendEmail(otpGeneratedEvent.getEmail(), utils.getAdmin().getEmail(), "Password Reset", Html.getPasswordResetHtml(otpGeneratedEvent.getOtp()));
    }
}
