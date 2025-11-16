package com.dikshanta.food.delivery.foodDeliveryBackend.handlers;

import com.dikshanta.food.delivery.foodDeliveryBackend.responses.ApiResponse;
import com.dikshanta.food.delivery.foodDeliveryBackend.responses.ExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Autowired
    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(401);
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .exceptionTime(LocalDateTime.now())
                .exceptionName("UnauthorizedException")
                .detailedMessage("You are not authorized")
                .customValidators(null)
                .build();
        ApiResponse<ExceptionResponse> apiResponse = ApiResponse.<ExceptionResponse>builder()
                .status(false)
                .httpStatus(HttpStatus.FORBIDDEN)
                .message("unauthorized ,please provide credentials")
                .responseObject(exceptionResponse)
                .build();
        objectMapper.writeValue(response.getOutputStream(), apiResponse);
    }
}
