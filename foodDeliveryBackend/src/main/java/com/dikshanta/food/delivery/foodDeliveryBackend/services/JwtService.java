package com.dikshanta.food.delivery.foodDeliveryBackend.services;

import com.dikshanta.food.delivery.foodDeliveryBackend.enums.Role;
import com.dikshanta.food.delivery.foodDeliveryBackend.utils.Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final Utils utils;

    private SecretKey generateKey() {
        byte[] secretBytes = Decoders.BASE64.decode(utils.getJwt().getSecret());
        return Keys.hmacShaKeyFor(secretBytes);
    }

    public String getJwt(String email, Role role) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 1000L * utils.getJwt().getExpiry()))
                .signWith(generateKey())
                .claim("Role", role)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token) {
        Claims claims = extractClaims(token);
        return claims.getSubject();
    }

    public boolean isExpired(String token) {
        Claims claims = extractClaims(token);
        return claims.getExpiration().before(new Date(System.currentTimeMillis()));
    }

    public boolean isValid(String token, UserDetails userDetails) {
        Claims claims = extractClaims(token);
        return !isExpired(token) && claims.getSubject().equals(userDetails.getUsername());
    }
}
