package com.newtrading.api.dto.auth.request;

public record OAuthLoginRequest(
    String provider,
    String accessToken
) {}
