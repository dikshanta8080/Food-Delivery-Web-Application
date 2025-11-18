package com.dikshanta.food.delivery.foodDeliveryBackend.loaders;

import com.dikshanta.food.delivery.foodDeliveryBackend.models.District;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.Municipality;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.Province;
import com.dikshanta.food.delivery.foodDeliveryBackend.repositories.DistrictRepository;
import com.dikshanta.food.delivery.foodDeliveryBackend.repositories.MunicipalityRepository;
import com.dikshanta.food.delivery.foodDeliveryBackend.repositories.ProvinceRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Arrays;

@Component
@Order(1)
public class JsonLoader implements CommandLineRunner {

    @Autowired
    private ProvinceRepository provinceRepo;

    @Autowired
    private DistrictRepository districtRepo;

    @Autowired
    private MunicipalityRepository municipalityRepo;

    @Override
    public void run(String... args) throws Exception {
        if (provinceRepo.count() > 0) {
            return;
        }

        ObjectMapper mapper = new ObjectMapper();

        InputStream is = new ClassPathResource("address.json").getInputStream();
        ProvinceJson[] provincesJson = mapper.readValue(is, ProvinceJson[].class);

        Arrays.stream(provincesJson).forEach(pj -> {
            Province province = new Province();
            province.setName(pj.getName().split(" ")[0]);
            provinceRepo.save(province);

            if (pj.getDistricts() != null) {
                Arrays.stream(pj.getDistricts()).forEach(dj -> {
                    District district = new District();
                    district.setName(dj.getName());
                    district.setProvince(province);
                    districtRepo.save(district);

                    if (dj.getCities() != null) {
                        Arrays.stream(dj.getCities()).forEach(mj -> {
                            Municipality municipality = new Municipality();
                            municipality.setName(mj.getName());
                            municipality.setDistrict(district);
                            municipalityRepo.save(municipality);
                        });
                    }
                });
            }
        });

    }

    @Data
    private static class ProvinceJson {
        @JsonProperty("province_id")
        private Long provinceId;

        private String name;

        private DistrictJson[] districts;
    }

    @Data
    private static class DistrictJson {
        @JsonProperty("district_id")
        private Long districtId;

        private String name;

        @JsonProperty("cities")
        private MunicipalityJson[] cities;
    }

    @Data
    private static class MunicipalityJson {
        @JsonProperty("municipality_id")
        private Long municipalityId;

        private String name;
    }
}
