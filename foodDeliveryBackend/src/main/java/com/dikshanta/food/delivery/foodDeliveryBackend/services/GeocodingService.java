package com.dikshanta.food.delivery.foodDeliveryBackend.services;

import com.dikshanta.food.delivery.foodDeliveryBackend.dtos.GeocodeCoordinates;
import com.dikshanta.food.delivery.foodDeliveryBackend.pojos.GeocodingPojo;
import com.dikshanta.food.delivery.foodDeliveryBackend.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GeocodingService {
    private final RestTemplate restTemplate;
    private final Utils utils;

    public GeocodeCoordinates geocodeAddress(String province, String district, String municipality) {

        String api = utils.getGeocode().getApi();
        api = api.replace("<PROVINCE>", province).replace("<DISTRICT>", district).replace("<MUNICIPALITY>", municipality);

        ResponseEntity<GeocodingPojo[]> exchange = restTemplate.exchange(api, HttpMethod.GET, null, GeocodingPojo[].class);
        GeocodingPojo[] pojos = exchange.getBody();
        if (pojos != null) {
            GeocodingPojo locationDetails = pojos[0];
            return GeocodeCoordinates.builder()
                    .latitude(Double.valueOf(locationDetails.getLat()))
                    .longitude(Double.valueOf(locationDetails.getLon()))
                    .build();
        }
        return null;


    }
}
