package com.dikshanta.food.delivery.foodDeliveryBackend.mappers;

import com.dikshanta.food.delivery.foodDeliveryBackend.dtos.GeocodeCoordinates;
import com.dikshanta.food.delivery.foodDeliveryBackend.functionalinterfaces.QuadFunction;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.Address;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.District;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.Municipality;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.Province;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper implements QuadFunction<Province, District, Municipality, GeocodeCoordinates, Address> {
    @Override
    public Address apply(Province province, District district, Municipality municipality, GeocodeCoordinates geocodeCoordinates) {
        return Address.builder()
                .province(province)
                .district(district)
                .municipality(municipality)
                .latitude(geocodeCoordinates.getLatitude())
                .longitude(geocodeCoordinates.getLongitude())
                .fullAddress(String.format("%s-%s %s Nepal", municipality.getName(), district.getName(), province.getName()))
                .build();

    }
}
