package com.dikshanta.food.delivery.foodDeliveryBackend.repositories;

import com.dikshanta.food.delivery.foodDeliveryBackend.models.Province;
import com.dikshanta.food.delivery.foodDeliveryBackend.projections.ProvinceProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
    @Query("SELECT p.id AS id,p.name AS name FROM Province p")
    List<ProvinceProjection> getAllProvince();
}
