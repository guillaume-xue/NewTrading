package com.newtrading.api.dto.auth.response;

public record AuthResponse(
    String accessToken,
    String tokenType,
    long expiresIn
) {
    // Constructeur compact pratique pour initialiser un Bearer par défaut
    public AuthResponse(String accessToken, long expiresIn) {
        this(accessToken, "Bearer", expiresIn);
    }
}
