package com.dikshanta.food.delivery.foodDeliveryBackend.exceptions;

import com.dikshanta.food.delivery.foodDeliveryBackend.responses.ApiResponse;
import com.dikshanta.food.delivery.foodDeliveryBackend.responses.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<ExceptionResponse>> handleAllException(Exception e) {
        HttpStatus httpStatus;
        httpStatus = HttpStatus.BAD_REQUEST;
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .exceptionName(e.getClass().getSimpleName())
                .exceptionTime(LocalDateTime.now())
                .detailedMessage(e.getMessage())
                .customValidators(null)
                .build();
        ApiResponse<ExceptionResponse> apiResponse = ApiResponse.<ExceptionResponse>builder()
                .status(false)
                .httpStatus(httpStatus)
                .message("Exception occurred")
                .responseObject(exceptionResponse)
                .build();
        return new ResponseEntity<>(apiResponse, httpStatus);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<ExceptionResponse>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errorMap = e.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .exceptionTime(LocalDateTime.now())
                .exceptionName(e.getClass().getSimpleName())
                .detailedMessage("Method argument not valid please provide all fields in the dto")
                .customValidators(errorMap)
                .build();
        ApiResponse<ExceptionResponse> exceptionResponseApiResponse = ApiResponse.<ExceptionResponse>builder()
                .status(false)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message("Exception occurred")
                .responseObject(exceptionResponse)
                .build();
        return new ResponseEntity<>(exceptionResponseApiResponse, exceptionResponseApiResponse.getHttpStatus());
    }
}
