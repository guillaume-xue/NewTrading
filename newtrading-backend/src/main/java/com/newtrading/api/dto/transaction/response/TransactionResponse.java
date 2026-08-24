package com.newtrading.api.dto.transaction.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record TransactionResponse(
    UUID id,
    UUID portfolioId,
    String assetCode,
    String orderDirection,
    BigDecimal quantity,
    BigDecimal executionPrice,
    BigDecimal totalAmount,
    OffsetDateTime executedAt
) {}
