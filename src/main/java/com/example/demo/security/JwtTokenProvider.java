package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {
    
    private final SecretKey key;
    private final long validityInMs;
    
    public JwtTokenProvider(String secret, long validityInMs) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.validityInMs = validityInMs;
    }
    
    public JwtTokenProvider() {
        this("mySecretKey123456789012345678901234567890", 86400000L); // 24 hours
    }
    
    public String generateToken(Authentication auth, Long userId, String email, String role) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMs);
        
        return Jwts.builder()
            .setSubject(email)
            .claim("userId", userId)
            .claim("email", email)
            .claim("role", role)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(key)
            .compact();
    }
    
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
    
    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.get("email", String.class);
    }
    
    public String getRoleFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.get("role", String.class);
    }
    
    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        Object userId = claims.get("userId");
        if (userId != null) {
            return Long.valueOf(userId.toString());
        }
        // Fallback to subject
        String subject = claims.getSubject();
        try {
            return Long.valueOf(subject);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}