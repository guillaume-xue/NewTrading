package com.newtrading.api.dto;

import com.newtrading.api.model.User;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record UserResponse(
    UUID id,
    String username,
    String email,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt
) {
    public static UserResponse fromEntity(User user) {
        return new UserResponse(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }
}
