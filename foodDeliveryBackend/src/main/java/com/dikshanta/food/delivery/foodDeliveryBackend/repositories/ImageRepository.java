package com.dikshanta.food.delivery.foodDeliveryBackend.repositories;

import com.dikshanta.food.delivery.foodDeliveryBackend.models.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<UserImage, Long> {
}
