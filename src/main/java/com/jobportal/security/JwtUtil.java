package com.jobportal.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String SECRET = "mysecretkeymysecretkeymysecretkey123";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // ✅ GENERATE TOKEN (UPDATED)
    public String generateToken(String email, String role, int userId) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("ROLE", role);
        claims.put("userId", userId); // 🔥 ADD THIS

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(key)
                .compact();
    }

    // ✅ EXTRACT EMAIL
    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    // ✅ EXTRACT ROLE
    public String extractRole(String token) {
        return extractAllClaims(token).get("ROLE", String.class);
    }

    // ✅ EXTRACT USER ID
    public Integer extractUserId(String token) {
        return extractAllClaims(token).get("userId", Integer.class);
    }

    // ✅ COMMON METHOD
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ✅ VALIDATE TOKEN
    public boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}