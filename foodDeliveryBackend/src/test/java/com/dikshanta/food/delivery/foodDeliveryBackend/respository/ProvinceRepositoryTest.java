package com.dikshanta.food.delivery.foodDeliveryBackend.respository;

import com.dikshanta.food.delivery.foodDeliveryBackend.repositories.UserRepository;
import com.dikshanta.food.delivery.foodDeliveryBackend.services.GeocodingService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class ProvinceRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GeocodingService geocodingService;


    @Test
    public void testProvinceRepository() {


    }
}
