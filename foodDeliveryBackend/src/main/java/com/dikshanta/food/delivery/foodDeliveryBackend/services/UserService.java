package com.dikshanta.food.delivery.foodDeliveryBackend.services;

import com.dikshanta.food.delivery.foodDeliveryBackend.dtos.GeocodeCoordinates;
import com.dikshanta.food.delivery.foodDeliveryBackend.dtos.UserRegistrationRequestDto;
import com.dikshanta.food.delivery.foodDeliveryBackend.dtos.UserRegistrationResponseDto;
import com.dikshanta.food.delivery.foodDeliveryBackend.exceptions.UserAlreadyExistsException;
import com.dikshanta.food.delivery.foodDeliveryBackend.mappers.AddressMapper;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.*;
import com.dikshanta.food.delivery.foodDeliveryBackend.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.dikshanta.food.delivery.foodDeliveryBackend.enums.Role.CUSTOMER;

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

    @Transactional
    public UserRegistrationResponseDto createCustomer(UserRegistrationRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new UserAlreadyExistsException("User with this email already exists");
        }
        Province province = provinceRepository.findById(requestDto.getProvinceId()).orElseThrow(() -> new IllegalStateException("Province with this id does not exists"));

        District district = districtRepository.findById(requestDto.getDistrictId()).orElseThrow(() -> new IllegalStateException("District with this id does not exists"));

        Municipality municipality = municipalityRepository.findById(requestDto.getMunicipalityId()).orElseThrow(() -> new IllegalStateException("Municipality with this id does not exists"));

        GeocodeCoordinates coordinates = geocodingService.geocodeAddress(province.getName(), district.getName(), municipality.getName());

        Address address = addressMapper.apply(province, district, municipality, coordinates);

        address.setDetailedAddress(requestDto.getDetailedAddress());

        User user = User.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .role(CUSTOMER)
                .profileImageUrl("\\uploads\\images\\user\\defaultImage.png")
                .build();
        user.addAddress(address);
        userRepository.save(user);
        return UserRegistrationResponseDto.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .role(CUSTOMER)
                .build();


    }
}
