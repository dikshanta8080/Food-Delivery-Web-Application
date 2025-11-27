package com.dikshanta.food.delivery.foodDeliveryBackend.services;

import com.dikshanta.food.delivery.foodDeliveryBackend.dtos.*;
import com.dikshanta.food.delivery.foodDeliveryBackend.events.OtpGeneratedEvent;
import com.dikshanta.food.delivery.foodDeliveryBackend.events.PasswordResetEvent;
import com.dikshanta.food.delivery.foodDeliveryBackend.exceptions.*;
import com.dikshanta.food.delivery.foodDeliveryBackend.mappers.UserMapper;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.PasswordResetToken;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.RefreshToken;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.User;
import com.dikshanta.food.delivery.foodDeliveryBackend.repositories.PasswordResetTokenRepository;
import com.dikshanta.food.delivery.foodDeliveryBackend.repositories.RefreshTokenRepository;
import com.dikshanta.food.delivery.foodDeliveryBackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final PasswordResetTokenService passwordResetTokenService;
    private final ApplicationEventPublisher eventPublisher;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final PasswordEncoder passwordEncoder;


    public LoginResponseDto login(LoginRequestDoo requestDoo) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDoo.getEmail(), requestDoo.getPassword()));

            User user = userRepository.findByEmail(requestDoo.getEmail()).orElseThrow(() -> new UserDoesNotExistsException("User with this email does not exists"));

            String jwt = jwtService.getJwt(user);
            RefreshToken refreshToken = refreshTokenService.getRefreshToken(user.getEmail());

            return LoginResponseDto.builder()
                    .user(userMapper.toDto(user))
                    .accessToken(jwt)
                    .refreshToken(refreshToken.getToken())
                    .build();

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Please provide valid username and password to login");
        }
    }


    public TokenRefreshResponseDoo refreshTheToken(TokenRefreshRequestDto requestDto) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(requestDto.getToken()).orElseThrow(() -> new RefreshTokenDoesNotExistsException("The token does not exists in the database"));
        if (refreshToken.getExpiry().isBefore(Instant.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new RefreshTokenExpiredException("The refresh token is already expired please login to get new one");
        }
        RefreshToken newRefreshToken = refreshTokenService.generateRefreshToken(refreshToken.getUser());
        refreshTokenRepository.delete(refreshToken);

        RefreshToken newSavedRefreshToken = refreshTokenRepository.save(newRefreshToken);

        String accessToken = jwtService.getJwt(newRefreshToken.getUser());
        return TokenRefreshResponseDoo.builder()
                .accessToken(accessToken)
                .refreshToken(newRefreshToken.getToken())
                .build();
    }

    public String forgetPassword(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UserDoesNotExistsException("User does not exists"));
        PasswordResetToken passwordResetToken = passwordResetTokenService.getPasswordResetToken(user);
        eventPublisher.publishEvent(new OtpGeneratedEvent(this, user.getEmail(), passwordResetToken.getToken()));
        return "Otp sent to the email";

    }

    public String resetPasswordConfirmation(PasswordResetRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail()).orElseThrow(() -> new UserDoesNotExistsException("User with this email does not exists"));
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByUserAndToken(user, requestDto.getOtp()).orElseThrow(() -> new TokenDoesNotExistsException("Token does not exists"));
        if (passwordResetToken.getExpiry().isBefore(Instant.now())) {
            passwordResetTokenRepository.delete(passwordResetToken);
            throw new OtpExpiredException("Otp is already expired");
        }
        user.setPassword(passwordEncoder.encode(requestDto.getNewPassword()));
        userRepository.save(user);
        passwordResetTokenRepository.delete(passwordResetToken);
        eventPublisher.publishEvent(new PasswordResetEvent(this, user.getEmail(), user.getName()));
        return "Password changed successfully";

    }


}
