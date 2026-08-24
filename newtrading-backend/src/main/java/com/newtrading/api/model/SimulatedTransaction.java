package com.newtrading.api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "simulated_transactions")
public class SimulatedTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false)
    private VirtualPortfolio portfolio;

    @Column(name = "asset_code", nullable = false, length = 12)
    private String assetCode;

    @Column(name = "order_direction", nullable = false, length = 4)
    private String orderDirection;

    @Column(nullable = false, precision = 18, scale = 8)
    private BigDecimal quantity;

    @Column(name = "execution_price", nullable = false, precision = 18, scale = 8)
    private BigDecimal executionPrice;

    @Column(name = "executed_at", nullable = false, updatable = false)
    private OffsetDateTime executedAt = OffsetDateTime.now();

    public SimulatedTransaction() {}

    public SimulatedTransaction(VirtualPortfolio portfolio, String assetCode, String orderDirection, BigDecimal quantity, BigDecimal executionPrice) {
        this.portfolio = portfolio;
        this.assetCode = assetCode;
        this.orderDirection = orderDirection;
        this.quantity = quantity;
        this.executionPrice = executionPrice;
        this.executedAt = OffsetDateTime.now();
    }

    // Getters et Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public VirtualPortfolio getPortfolio() { return portfolio; }
    public void setPortfolio(VirtualPortfolio portfolio) { this.portfolio = portfolio; }

    public String getAssetCode() { return assetCode; }
    public void setAssetCode(String assetCode) { this.assetCode = assetCode; }

    public String getOrderDirection() { return orderDirection; }
    public void setOrderDirection(String orderDirection) { this.orderDirection = orderDirection; }

    public BigDecimal getQuantity() { return quantity; }
    public void setQuantity(BigDecimal quantity) { this.quantity = quantity; }

    public BigDecimal getExecutionPrice() { return executionPrice; }
    public void setExecutionPrice(BigDecimal executionPrice) { this.executionPrice = executionPrice; }

    public OffsetDateTime getExecutedAt() { return executedAt; }
    public void setExecutedAt(OffsetDateTime executedAt) { this.executedAt = executedAt; }
}
