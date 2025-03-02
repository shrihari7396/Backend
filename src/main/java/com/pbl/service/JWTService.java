package com.pbl.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

public interface JWTService {
    String generateToken(String username);
    SecretKey getKey();
    String extractUserName(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimResolver);
    Claims extractAllClaims(String token);
    boolean validateToken(String token, UserDetails userDetails);
    boolean isTokenExpired(String token);
    Date extractExpiration(String token);
}
