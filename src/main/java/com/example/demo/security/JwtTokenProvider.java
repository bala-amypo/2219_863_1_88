package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component   
public class JwtTokenProvider {

    private final String jwtSecret;
    private final long jwtExpirationMs;

    public JwtTokenProvider() {
        this.jwtSecret = "MySuperSecretJwtKeyForApartmentSystem123456";
        this.jwtExpirationMs = 3600000L;
    }

    public JwtTokenProvider(String secret, long expiration) {
        this.jwtSecret = secret;
        this.jwtExpirationMs = expiration;
    }

    public String generateToken(org.springframework.security.core.Authentication authentication,
                                Long userId, String email, String role) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("email", email)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(jwtSecret.getBytes())
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret.getBytes())
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .get("email", String.class);
    }

    public String getRoleFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }
}
