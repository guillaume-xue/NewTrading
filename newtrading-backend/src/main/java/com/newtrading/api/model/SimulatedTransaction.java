package com.newtrading.api.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "simulated_transactions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SimulatedTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false)
    private VirtualPortfolio portfolio;

    @Column(name = "asset_code", length = 12, nullable = false)
    private String assetCode;

    @Column(name = "order_direction", length = 4, nullable = false)
    private String orderDirection; // "BUY" ou "SELL"

    @Column(precision = 18, scale = 8, nullable = false)
    private BigDecimal quantity;

    @Column(name = "execution_price", precision = 18, scale = 8, nullable = false)
    private BigDecimal executionPrice;

    @Column(name = "executed_at", nullable = false)
    private OffsetDateTime executedAt;

    @PrePersist
    public void prePersist() {
        if (executedAt == null) {
            executedAt = OffsetDateTime.now();
        }
    }
}
