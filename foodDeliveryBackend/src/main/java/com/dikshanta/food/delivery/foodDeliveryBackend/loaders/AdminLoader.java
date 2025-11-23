package com.dikshanta.food.delivery.foodDeliveryBackend.loaders;

import com.dikshanta.food.delivery.foodDeliveryBackend.dtos.GeocodeCoordinates;
import com.dikshanta.food.delivery.foodDeliveryBackend.exceptions.DistrictNotFoundException;
import com.dikshanta.food.delivery.foodDeliveryBackend.exceptions.MunicipalityNotFoundException;
import com.dikshanta.food.delivery.foodDeliveryBackend.exceptions.ProvinceNotFoundException;
import com.dikshanta.food.delivery.foodDeliveryBackend.mappers.AddressMapper;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.*;
import com.dikshanta.food.delivery.foodDeliveryBackend.repositories.*;
import com.dikshanta.food.delivery.foodDeliveryBackend.services.GeocodingService;
import com.dikshanta.food.delivery.foodDeliveryBackend.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.dikshanta.food.delivery.foodDeliveryBackend.enums.Role.ADMIN;

@Component
@RequiredArgsConstructor
@Order(2)
public class AdminLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final Utils utils;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;
    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;
    private final MunicipalityRepository municipalityRepository;
    private final GeocodingService geocodingService;
    private final AddressMapper addressMapper;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void run(String... args) throws Exception {
        if (!userRepository.existsByEmail(utils.getAdmin().getEmail())) {
            Province province = getProvince();
            District district = getDistrict();
            Municipality municipality = getMunicipality();
            GeocodeCoordinates geocodeCoordinates = getGeocodeCoordinates(province, district, municipality);
            Address address = addressMapper.apply(province, district, municipality, geocodeCoordinates);
            address.setDetailedAddress("Itahari 17 kanchichowk near police station in chatara road");
            User admin = getUser();
            admin.addAddress(address);
            userRepository.save(admin);

        }
    }

    //Helper private methods
    private User getUser() {
        User admin = User.builder()
                .name(utils.getAdmin().getName())
                .email(utils.getAdmin().getEmail())
                .password(passwordEncoder.encode(utils.getAdmin().getPassword()))
                .role(ADMIN)
                .profileImageUrl("\\uploads\\images\\user\\admin.png")
                .build();
        return admin;
    }

    private GeocodeCoordinates getGeocodeCoordinates(Province province, District district, Municipality municipality) {
        GeocodeCoordinates geocodeCoordinates = geocodingService.geocodeAddress(province.getName(), district.getName(), municipality.getName());
        return geocodeCoordinates;
    }

    private Municipality getMunicipality() {
        Municipality municipality = municipalityRepository.findByName("Itahari").orElseThrow(() -> new MunicipalityNotFoundException("Municipality 'Itahari' not found"));
        return municipality;
    }

    private District getDistrict() {
        District district = districtRepository.findByName("Sunsari").orElseThrow(() -> new DistrictNotFoundException("District 'Sunsari' not found"));
        return district;
    }

    private Province getProvince() {
        Province province = provinceRepository.findByName("Koshi").orElseThrow(() -> new ProvinceNotFoundException("Province 'koshi' not found"));
        return province;
    }
}
