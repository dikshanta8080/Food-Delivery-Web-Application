package com.dikshanta.food.delivery.foodDeliveryBackend.services;

import com.dikshanta.food.delivery.foodDeliveryBackend.dtos.AddDeliveryRequestDto;
import com.dikshanta.food.delivery.foodDeliveryBackend.dtos.AddDeliveryResponseDto;
import com.dikshanta.food.delivery.foodDeliveryBackend.dtos.GeocodeCoordinates;
import com.dikshanta.food.delivery.foodDeliveryBackend.exceptions.*;
import com.dikshanta.food.delivery.foodDeliveryBackend.mappers.AddDeliveryAddressResponseDtoMapper;
import com.dikshanta.food.delivery.foodDeliveryBackend.mappers.AddressMapper;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.*;
import com.dikshanta.food.delivery.foodDeliveryBackend.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;
    private final MunicipalityRepository municipalityRepository;
    private final AddressMapper addressMapper;
    private final GeocodingService geocodingService;
    private final AddDeliveryAddressResponseDtoMapper deliveryAddressResponseDtoMapper;

    public AddDeliveryResponseDto addDeliveryAddress(AddDeliveryRequestDto requestDto) {
        //start
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        log.debug(userPrincipal.getUsername());
        //end
        
        // currently logged-in user
        User user = getUser(userPrincipal.getId());
        Province province = getProvince(requestDto.getProvinceId());
        District district = getDistrict(requestDto.getDistrictId());
        Municipality municipality = getMunicipality(requestDto.getMunicipalityId());

        GeocodeCoordinates coordinates = extractCoordinates(province, district, municipality);

        Address address = addressMapper.apply(province, district, municipality, coordinates);
        address.setDetailedAddress(requestDto.getDetailedAddress());
        user.addAddress(address);
        address.setUser(user);
        userRepository.save(user);
        return deliveryAddressResponseDtoMapper.apply(province, district, municipality, address);
    }

    private GeocodeCoordinates extractCoordinates(Province province, District district, Municipality municipality) {
        try {
            return geocodingService.geocodeAddress(province.getName(), district.getName(), municipality.getName());
        } catch (Exception e) {
            throw new CoordinatesInvalidException(
                    "Failed to geocode address: " + municipality.getName() + ", " +
                            district.getName() + ", " + province.getName(), e);
        }
    }

    private Municipality getMunicipality(Long municipalityId) {
        return municipalityRepository.findById(municipalityId).orElseThrow(() ->
                new MunicipalityNotFoundException("Municipality with this id does not exists"));

    }

    private District getDistrict(Long districtId) {
        return districtRepository.findById(districtId).orElseThrow(() ->
                new DistrictNotFoundException("District with this id does not exists"));
    }

    private Province getProvince(Long provinceId) {
        return provinceRepository.findById(provinceId).orElseThrow(() ->
                new ProvinceNotFoundException("Province with this id does not exists"));

    }

    private User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new UserDoesNotExistsException("User with this id does not exists"));

    }
}
