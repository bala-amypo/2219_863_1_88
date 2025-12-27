package com.example.demo.util;

public final class JwtConstant {

    private JwtConstant() {
    }

    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String SECRET_KEY = "MySecretKey123456789";
    public static final long EXPIRATION_TIME = 86400000; // 24 hours
}