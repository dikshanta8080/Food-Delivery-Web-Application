package com.dikshanta.food.delivery.foodDeliveryBackend.mappers;

import com.dikshanta.food.delivery.foodDeliveryBackend.dtos.AddDeliveryResponseDto;
import com.dikshanta.food.delivery.foodDeliveryBackend.functionalinterfaces.QuadFunction;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.Address;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.District;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.Municipality;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.Province;
import org.springframework.stereotype.Component;

@Component
public class AddDeliveryAddressResponseDtoMapper implements QuadFunction<Province, District, Municipality, Address, AddDeliveryResponseDto> {

    @Override
    public AddDeliveryResponseDto apply(Province province, District district, Municipality municipality, Address address) {
        return AddDeliveryResponseDto.builder()
                .province(province.getName())
                .municipality(municipality.getName())
                .district(district.getName())
                .fullAddress(address.getFullAddress())
                .detailedAddress(address.getDetailedAddress())
                .build();
    }

}
