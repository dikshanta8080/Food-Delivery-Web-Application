package com.dikshanta.food.delivery.foodDeliveryBackend.controllers;

import com.dikshanta.food.delivery.foodDeliveryBackend.dtos.UserRegistrationRequestDto;
import com.dikshanta.food.delivery.foodDeliveryBackend.dtos.UserRegistrationResponseDto;
import com.dikshanta.food.delivery.foodDeliveryBackend.responses.ApiResponse;
import com.dikshanta.food.delivery.foodDeliveryBackend.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

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
}
