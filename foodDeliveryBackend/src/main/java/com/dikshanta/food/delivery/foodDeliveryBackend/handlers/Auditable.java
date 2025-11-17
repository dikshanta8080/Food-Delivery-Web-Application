package com.dikshanta.food.delivery.foodDeliveryBackend.handlers;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

public abstract class Auditable {
    @CreatedDate()
    private String createdAt;
    @LastModifiedDate
    private String lastModifiedAt;
}
