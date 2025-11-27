package com.dikshanta.food.delivery.foodDeliveryBackend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController {
    @GetMapping("/check")
    public ResponseEntity<String> checkHealth(HttpServletRequest request) {
        String response = String.format("Tomcat is running in 8080 with session if %s", request.getSession().getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
