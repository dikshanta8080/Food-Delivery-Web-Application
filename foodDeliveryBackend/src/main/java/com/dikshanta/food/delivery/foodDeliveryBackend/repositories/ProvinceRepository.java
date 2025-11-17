package com.dikshanta.food.delivery.foodDeliveryBackend.repositories;

import com.dikshanta.food.delivery.foodDeliveryBackend.dtos.ProvinceDto;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
    @Query("SELECT new com.dikshanta.food.delivery.foodDeliveryBackend.dtos.ProvinceDto(" +
            " p.id,p.name) FROM Province p")
    List<ProvinceDto> getAllProvince();
}
