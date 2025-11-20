package com.dikshanta.food.delivery.foodDeliveryBackend.services;

import com.dikshanta.food.delivery.foodDeliveryBackend.dtos.LoginRequestDoo;
import com.dikshanta.food.delivery.foodDeliveryBackend.dtos.LoginResponseDto;
import com.dikshanta.food.delivery.foodDeliveryBackend.dtos.TokenRefreshRequestDto;
import com.dikshanta.food.delivery.foodDeliveryBackend.dtos.TokenRefreshResponseDoo;
import com.dikshanta.food.delivery.foodDeliveryBackend.exceptions.RefreshTokenDoesNotExistsException;
import com.dikshanta.food.delivery.foodDeliveryBackend.exceptions.RefreshTokenExpiredException;
import com.dikshanta.food.delivery.foodDeliveryBackend.exceptions.UserDoesNotExistsException;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.RefreshToken;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.User;
import com.dikshanta.food.delivery.foodDeliveryBackend.repositories.RefreshTokenRepository;
import com.dikshanta.food.delivery.foodDeliveryBackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
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


    public LoginResponseDto login(LoginRequestDoo requestDoo) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDoo.getEmail(), requestDoo.getPassword()));

            User user = userRepository.findByEmail(requestDoo.getEmail()).orElseThrow(() -> new UserDoesNotExistsException("User with this email does not exists"));

            String jwt = jwtService.getJwt(user.getEmail(), user.getRole());
            RefreshToken refreshToken = refreshTokenService.getRefreshToken(user.getEmail());

            return LoginResponseDto.builder()
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

        String accessToken = jwtService.getJwt(newRefreshToken.getUser().getEmail(), newSavedRefreshToken.getUser().getRole());
        return TokenRefreshResponseDoo.builder()
                .accessToken(accessToken)
                .refreshToken(newRefreshToken.getToken())
                .build();
    }
}
