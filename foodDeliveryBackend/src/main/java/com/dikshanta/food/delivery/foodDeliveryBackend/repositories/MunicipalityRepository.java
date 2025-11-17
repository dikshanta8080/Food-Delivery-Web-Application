package com.dikshanta.food.delivery.foodDeliveryBackend.repositories;

import com.dikshanta.food.delivery.foodDeliveryBackend.dtos.MunicipalityDto;
import com.dikshanta.food.delivery.foodDeliveryBackend.models.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality, Long> {
    @Query("""
            SELECT  new com.dikshanta.food.delivery.foodDeliveryBackend.dtos.MunicipalityDto 
            (m.id AS id,
                   m.name AS name)
            FROM Municipality m
            """)
    List<MunicipalityDto> findAllMunicipalities();

    @Query("""
            SELECT new com.dikshanta.food.delivery.foodDeliveryBackend.dtos.MunicipalityDto 
            (m.id,
                   m.name)
            FROM Municipality m
            WHERE m.district.id = :districtId
            """)
    List<MunicipalityDto> findMunicipalityByDistrict(@Param("districtId") Long districtId);


    @Query("""
            SELECT new com.dikshanta.food.delivery.foodDeliveryBackend.dtos.MunicipalityDto 
            (m.id,
                   m.name)
            FROM Municipality m
            JOIN District d ON m.district.id = d.id
            JOIN Province p ON p.id = d.province.id
            WHERE p.id = :id
            """)
    List<MunicipalityDto> findMunicipalityByProvince(@Param("id") Long id);

}
