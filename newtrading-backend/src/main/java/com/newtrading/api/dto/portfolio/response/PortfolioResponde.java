package com.newtrading.api.dto.portfolio.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record PortfolioResponse(
    UUID id,
    UUID userId,
    BigDecimal currentBalance,
    BigDecimal initialBalance,
    OffsetDateTime createdAt
) {}
