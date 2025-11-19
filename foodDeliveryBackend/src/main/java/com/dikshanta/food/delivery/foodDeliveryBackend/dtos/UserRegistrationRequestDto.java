package com.dikshanta.food.delivery.foodDeliveryBackend.dtos;

import com.dikshanta.food.delivery.foodDeliveryBackend.validators.EmailValidator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserRegistrationRequestDto {
    @NotBlank(message = "Please provide non blank and valid name")
    private String name;
    @EmailValidator
    private String email;
    @NotBlank(message = "Please provide non blank and valid password")
    private String password;
    @NotNull(message = "Please provide valid province id")
    private Long provinceId;
    @NotNull(message = "Please provide valid district id")
    @NotNull(message = "Please provide valid district id")
    private Long districtId;
    @NotNull(message = "Please provide valid municipality id")
    private Long municipalityId;
    @NotBlank(message = "please provide the details of your address")
    private String detailedAddress;
}
