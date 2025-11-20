package com.dikshanta.food.delivery.foodDeliveryBackend.controllers;

import com.dikshanta.food.delivery.foodDeliveryBackend.dtos.*;
import com.dikshanta.food.delivery.foodDeliveryBackend.responses.ApiResponse;
import com.dikshanta.food.delivery.foodDeliveryBackend.services.AuthService;
import com.dikshanta.food.delivery.foodDeliveryBackend.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserRegistrationResponseDto>> createCustomer(@Valid @RequestBody UserRegistrationRequestDto requestDto) {
        UserRegistrationResponseDto customer = userService.createCustomer(requestDto);
        ApiResponse<UserRegistrationResponseDto> apiResponse = ApiResponse.<UserRegistrationResponseDto>builder()
                .status(true)
                .httpStatus(HttpStatus.CREATED)
                .message("Successfully created the customer")
                .responseObject(customer)
                .build();
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDto>> login(@Valid @RequestBody LoginRequestDoo requestDoo) {
        LoginResponseDto loginResponseDto = authService.login(requestDoo);
        ApiResponse<LoginResponseDto> apiResponse = ApiResponse.<LoginResponseDto>builder()
                .status(true)
                .httpStatus(HttpStatus.OK)
                .message("successfully authenticated")
                .responseObject(loginResponseDto)

                .build();
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<TokenRefreshResponseDoo>> refreshTheToken(@Valid @RequestBody TokenRefreshRequestDto requestDto) {
        TokenRefreshResponseDoo tokenRefreshResponseDoo = authService.refreshTheToken(requestDto);
        ApiResponse<TokenRefreshResponseDoo> apiResponse = ApiResponse.<TokenRefreshResponseDoo>builder()
                .status(true)
                .httpStatus(HttpStatus.OK)
                .message("Refreshed the tokens")
                .responseObject(tokenRefreshResponseDoo)
                .build();
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }
}
