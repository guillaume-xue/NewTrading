package com.newtrading.api.dto;

import com.newtrading.api.model.SimulatedTransaction;

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
) {
    public static TransactionResponse fromEntity(SimulatedTransaction transaction) {
        BigDecimal total = transaction.getExecutionPrice().multiply(transaction.getQuantity());
        return new TransactionResponse(
            transaction.getId(),
            transaction.getPortfolio().getId(),
            transaction.getAssetCode(),
            transaction.getOrderDirection(),
            transaction.getQuantity(),
            transaction.getExecutionPrice(),
            total,
            transaction.getExecutedAt()
        );
    }
}
