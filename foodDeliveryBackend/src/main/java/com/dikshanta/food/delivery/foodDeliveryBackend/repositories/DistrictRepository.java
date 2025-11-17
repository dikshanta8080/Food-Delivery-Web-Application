package com.dikshanta.food.delivery.foodDeliveryBackend.repositories;

import com.dikshanta.food.delivery.foodDeliveryBackend.dtos.DistrictDto;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.District;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Location> {
    @Query("SELECT new com.dikshanta.food.delivery.foodDeliveryBackend.dtos.DistrictDto(d.id, d.name) " +
            "FROM District d WHERE d.province.id = :province")
    List<DistrictDto> findByDistrictByProvinceId(@Param("province") Long provinceId);


    @Query("SELECT new com.dikshanta.food.delivery.foodDeliveryBackend.dtos.DistrictDto(d.id, d.name) " +
            "FROM District d")
    List<DistrictDto> findAllDistrict();


}
