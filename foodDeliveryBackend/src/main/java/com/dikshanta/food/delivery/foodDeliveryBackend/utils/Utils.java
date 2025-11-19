package com.dikshanta.food.delivery.foodDeliveryBackend.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Getter
@Component
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "utils")
public class Utils {
    private Admin admin;
    private Jwt jwt;
    private Refresh refresh;
    private Geocode geocode;

    @Data
    @Getter
    public static class Admin {
        private String name;
        private String email;
        private String profileImagePath;
        private String password;
    }

    @Data
    @Getter
    public static class Jwt {
        private String secret;
        private int expiry;
    }

    @Data
    @Getter
    public static class Refresh {
        private int expiry;
    }

    @Data
    @Getter
    public static class Geocode {
        private String api;
    }
}
