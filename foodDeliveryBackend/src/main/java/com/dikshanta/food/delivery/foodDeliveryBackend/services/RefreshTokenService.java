package com.dikshanta.food.delivery.foodDeliveryBackend.services;

import com.dikshanta.food.delivery.foodDeliveryBackend.exceptions.UserDoesNotExistsException;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.RefreshToken;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.User;
import com.dikshanta.food.delivery.foodDeliveryBackend.repositories.RefreshTokenRepository;
import com.dikshanta.food.delivery.foodDeliveryBackend.repositories.UserRepository;
import com.dikshanta.food.delivery.foodDeliveryBackend.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final Utils utils;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    RefreshToken generateRefreshToken(User user) {
        return RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .user(user)
                .expiry(Instant.now().plusSeconds(60 * 60L * 24 * utils.getRefresh().getExpiry()))
                .build();
    }


    public RefreshToken getRefreshToken(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserDoesNotExistsException("User with this email does not exists"));
        Optional<RefreshToken> refreshTokenOptional = refreshTokenRepository.findByUser(user);
        refreshTokenOptional.ifPresent(refreshTokenRepository::delete);
        return refreshTokenRepository.save(generateRefreshToken(user));
    }
}
