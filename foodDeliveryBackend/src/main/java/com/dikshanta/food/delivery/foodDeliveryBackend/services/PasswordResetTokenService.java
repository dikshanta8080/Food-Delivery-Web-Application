package com.dikshanta.food.delivery.foodDeliveryBackend.services;

import com.dikshanta.food.delivery.foodDeliveryBackend.models.PasswordResetToken;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.User;
import com.dikshanta.food.delivery.foodDeliveryBackend.repositories.PasswordResetTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PasswordResetTokenService {
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    public PasswordResetToken getPasswordResetToken(User user) {
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);
        PasswordResetToken passwordResetToken = PasswordResetToken.builder()
                .token(otp)
                .user(user)
                .expiry(Instant.now().plusSeconds(5000))
                .build();
        return passwordResetTokenRepository.save(passwordResetToken);
    }


}
