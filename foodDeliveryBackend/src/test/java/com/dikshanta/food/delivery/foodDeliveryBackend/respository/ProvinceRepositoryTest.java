package com.dikshanta.food.delivery.foodDeliveryBackend.respository;

import com.dikshanta.food.delivery.foodDeliveryBackend.repositories.DistrictRepository;
import com.dikshanta.food.delivery.foodDeliveryBackend.repositories.MunicipalityRepository;
import com.dikshanta.food.delivery.foodDeliveryBackend.repositories.ProvinceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProvinceRepositoryTest {
    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;
    private final MunicipalityRepository municipalityRepository;

    @Autowired
    public ProvinceRepositoryTest(ProvinceRepository provinceRepository, DistrictRepository districtRepository, MunicipalityRepository municipalityRepository) {
        this.provinceRepository = provinceRepository;
        this.districtRepository = districtRepository;
        this.municipalityRepository = municipalityRepository;
    }

    @Test
    public void testProvinceRepository() {
        municipalityRepository.findMunicipalityByProvince(1L).forEach(System.out::println);

    }
}
