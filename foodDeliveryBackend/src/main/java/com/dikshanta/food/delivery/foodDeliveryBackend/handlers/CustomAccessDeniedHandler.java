package com.dikshanta.food.delivery.foodDeliveryBackend.handlers;

import com.dikshanta.food.delivery.foodDeliveryBackend.responses.ApiResponse;
import com.dikshanta.food.delivery.foodDeliveryBackend.responses.ExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper;

    @Autowired
    public CustomAccessDeniedHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(403);
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .exceptionTime(LocalDateTime.now())
                .exceptionName("AccessDeniedException")
                .detailedMessage("You do not have authority to access the route")
                .customValidators(null)
                .build();
        ApiResponse<ExceptionResponse> apiResponse = ApiResponse.<ExceptionResponse>builder()
                .status(false)
                .httpStatus(HttpStatus.FORBIDDEN)
                .message("Access denied for this route")
                .responseObject(exceptionResponse)
                .build();
        objectMapper.writeValue(response.getOutputStream(), apiResponse);
    }
}
