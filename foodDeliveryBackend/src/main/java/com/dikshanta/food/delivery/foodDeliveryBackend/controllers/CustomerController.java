package com.dikshanta.food.delivery.foodDeliveryBackend.controllers;

import com.dikshanta.food.delivery.foodDeliveryBackend.dtos.AddDeliveryRequestDto;
import com.dikshanta.food.delivery.foodDeliveryBackend.dtos.AddDeliveryResponseDto;
import com.dikshanta.food.delivery.foodDeliveryBackend.responses.ApiResponse;
import com.dikshanta.food.delivery.foodDeliveryBackend.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final AddressService addressService;

    @PostMapping("/add-address")
    public ResponseEntity<ApiResponse<AddDeliveryResponseDto>> addDeliveryAddress(AddDeliveryRequestDto requestDto) {
        AddDeliveryResponseDto addDeliveryResponseDto = addressService.addDeliveryAddress(requestDto);
        ApiResponse<AddDeliveryResponseDto> apiResponse = ApiResponse.<AddDeliveryResponseDto>builder()
                .status(true)
                .httpStatus(HttpStatus.OK)
                .message("Added the new Address")
                .responseObject(addDeliveryResponseDto)
                .build();
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());

    }

}
