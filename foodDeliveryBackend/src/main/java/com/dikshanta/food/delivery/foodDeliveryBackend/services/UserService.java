package com.dikshanta.food.delivery.foodDeliveryBackend.services;

import com.dikshanta.food.delivery.foodDeliveryBackend.dtos.UserRegistrationRequestDto;
import com.dikshanta.food.delivery.foodDeliveryBackend.dtos.UserRegistrationResponseDto;
import com.dikshanta.food.delivery.foodDeliveryBackend.exceptions.UserAlreadyExistsException;
import com.dikshanta.food.delivery.foodDeliveryBackend.mappers.AddressMapper;
import com.dikshanta.food.delivery.foodDeliveryBackend.mappers.UserMapper;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.User;
import com.dikshanta.food.delivery.foodDeliveryBackend.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.dikshanta.food.delivery.foodDeliveryBackend.enums.Role.CUSTOMER;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;
    private final MunicipalityRepository municipalityRepository;
    private final AddressMapper addressMapper;
    private final GeocodingService geocodingService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Transactional
    public UserRegistrationResponseDto createCustomer(UserRegistrationRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new UserAlreadyExistsException("User with this email already exists");
        }
        User user = userMapper.toUser(requestDto);
        user.setProfileImageUrl("\\uploads\\images\\user\\defaultImage.png");
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setRole(CUSTOMER);
        return userMapper.toDto(userRepository.save(user));
    }


}
