package com.dikshanta.food.delivery.foodDeliveryBackend.repositories;

import com.dikshanta.food.delivery.foodDeliveryBackend.models.District;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.Location;
import com.dikshanta.food.delivery.foodDeliveryBackend.projections.DistrictProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Location> {
    @Query("SELECT d.id AS id,d.name AS name FROM District d WHERE d.province.id=:province")
    List<DistrictProjection> findByDistrictByProvinceId(@Param(value = "province") Long provinceId);

    @Query("SELECT d.id AS id,d.name AS name FROM District d")
    List<DistrictProjection> findAllDistrict();

}
