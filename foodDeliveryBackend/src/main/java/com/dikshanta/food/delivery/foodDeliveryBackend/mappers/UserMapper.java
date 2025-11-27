package com.dikshanta.food.delivery.foodDeliveryBackend.mappers;

import com.dikshanta.food.delivery.foodDeliveryBackend.dtos.UserRegistrationRequestDto;
import com.dikshanta.food.delivery.foodDeliveryBackend.dtos.UserRegistrationResponseDto;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserRegistrationResponseDto toDto(User user) {
        if (user == null) return null;
        return UserRegistrationResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .profileImageUrl(user.getProfileImageUrl())
                .build();
    }

    public User toUser(UserRegistrationRequestDto requestDto) {
        if (requestDto == null) return null;
        User user = new User();
        user.setName(requestDto.getName());
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        user.setRole(null);
        user.setProfileImageUrl(null);
        return user;
    }
}
