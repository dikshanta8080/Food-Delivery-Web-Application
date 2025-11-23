package com.dikshanta.food.delivery.foodDeliveryBackend.mappers;

import com.dikshanta.food.delivery.foodDeliveryBackend.dtos.UserRegistrationRequestDto;
import com.dikshanta.food.delivery.foodDeliveryBackend.dtos.UserRegistrationResponseDto;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserRegistrationResponseDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password", ignore = true)
    User toUser(UserRegistrationRequestDto requestDto);
}
