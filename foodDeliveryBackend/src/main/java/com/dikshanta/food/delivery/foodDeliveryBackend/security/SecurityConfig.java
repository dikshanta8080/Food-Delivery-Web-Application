package com.dikshanta.food.delivery.foodDeliveryBackend.security;

import com.dikshanta.food.delivery.foodDeliveryBackend.filters.JwtFilter;
import com.dikshanta.food.delivery.foodDeliveryBackend.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static com.dikshanta.food.delivery.foodDeliveryBackend.enums.Permission.*;
import static com.dikshanta.food.delivery.foodDeliveryBackend.enums.Role.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final String adminRoute = "/api/v1/admin/**";
    private static final String restaurantManagementRoute = "/api/v1/restaurant/**";
    private static final String publicRoute = "/api/v1/public/**";
    private static final String authRoute = "/api/v1/auth/**";
    private static final String customerRoute = "/api/v1/customer/**";
    private final AccessDeniedHandler accessDeniedHandler;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityConfig(@Qualifier("customAccessDeniedHandler") AccessDeniedHandler accessDeniedHandler, @Qualifier("customAuthenticationEntryPoint") AuthenticationEntryPoint authenticationEntryPoint, JwtFilter jwtFilter) {
        this.accessDeniedHandler = accessDeniedHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(req -> req
                        .requestMatchers(authRoute).permitAll()
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**"
                        ).permitAll()
                        .requestMatchers(adminRoute)
                        .hasRole(ADMIN.name())
                        .requestMatchers("GET", adminRoute).hasAuthority(ADMIN_READ.getPermissionName())
                        .requestMatchers("POST", adminRoute).hasAuthority(ADMIN_CREATE.getPermissionName())
                        .requestMatchers("PUT", adminRoute).hasAuthority(ADMIN_UPDATE.getPermissionName())
                        .requestMatchers("DELETE", adminRoute).hasAuthority(ADMIN_DELETE.getPermissionName())

                        .requestMatchers(customerRoute).hasAnyRole(ADMIN.name(), RESTAURANT.name(), CUSTOMER.name())
                        .requestMatchers("GET", customerRoute).hasAnyAuthority(ADMIN_READ.getPermissionName(), RESTAURANT_READ.getPermissionName(), CUSTOMER_READ.getPermissionName())
                        .requestMatchers("POST", customerRoute).hasAnyAuthority(ADMIN_CREATE.getPermissionName(), RESTAURANT_CREATE.getPermissionName(), CUSTOMER_CREATE.getPermissionName())
                        .requestMatchers("PUT", customerRoute).hasAnyAuthority(ADMIN_UPDATE.getPermissionName(), RESTAURANT_UPDATE.getPermissionName(), CUSTOMER_UPDATE.getPermissionName())
                        .requestMatchers("DELETE", customerRoute).hasAnyAuthority(ADMIN_DELETE.getPermissionName(), RESTAURANT_DELETE.getPermissionName(), CUSTOMER_DELETE.getPermissionName())

                        .requestMatchers(restaurantManagementRoute)
                        .hasAnyRole(ADMIN.name(), RESTAURANT.name())
                        .requestMatchers("GET", restaurantManagementRoute).hasAnyAuthority(ADMIN_READ.getPermissionName(), RESTAURANT_READ.getPermissionName())
                        .requestMatchers("POST", restaurantManagementRoute).hasAnyAuthority(ADMIN_CREATE.getPermissionName(), RESTAURANT_CREATE.getPermissionName())
                        .requestMatchers("PUT", restaurantManagementRoute).hasAnyAuthority(ADMIN_UPDATE.getPermissionName(), RESTAURANT_UPDATE.getPermissionName())
                        .requestMatchers("DELETE", restaurantManagementRoute).hasAnyAuthority(ADMIN_DELETE.getPermissionName(), RESTAURANT_DELETE.getPermissionName())
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(ex -> ex.accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(authenticationEntryPoint))
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH"));
        corsConfiguration.setAllowedOriginPatterns(List.of("http://localhost:5173/"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
