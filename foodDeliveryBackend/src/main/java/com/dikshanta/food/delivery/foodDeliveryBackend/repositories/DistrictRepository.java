package com.dikshanta.food.delivery.foodDeliveryBackend.repositories;

import com.dikshanta.food.delivery.foodDeliveryBackend.models.District;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.Location;
import com.dikshanta.food.delivery.foodDeliveryBackend.projections.ProvinceProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District, Location> {
    @Query("SELECT d.id AS districtId,d.name AS districtName FROM District d WHERE d.province=:province")
    Optional<ProvinceProjection> findByProvinceId(@Param(value = "province") Long provinceId);
}
