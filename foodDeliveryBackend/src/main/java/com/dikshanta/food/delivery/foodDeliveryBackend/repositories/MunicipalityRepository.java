package com.dikshanta.food.delivery.foodDeliveryBackend.repositories;

import com.dikshanta.food.delivery.foodDeliveryBackend.models.Municipality;
import com.dikshanta.food.delivery.foodDeliveryBackend.projections.MunicipalityProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality, Long> {
    @Query("""
            SELECT m.id AS id,
                   m.name AS name
            FROM Municipality m
            """)
    List<MunicipalityProjection> findAllMunicipalities();

    @Query("""
            SELECT m.id AS id,
                   m.name AS name
            FROM Municipality m
            WHERE m.district.id = :districtId
            """)
    List<MunicipalityProjection> findMunicipalityByDistrict(@Param("districtId") Long districtId);


    @Query("""
            SELECT m.id AS id,
                   m.name AS name
            FROM Municipality m
            JOIN District d ON m.district.id = d.id
            JOIN Province p ON p.id = d.province.id
            WHERE p.id = :id
            """)
    List<MunicipalityProjection> findMunicipalityByProvince(@Param("id") Long id);

}
