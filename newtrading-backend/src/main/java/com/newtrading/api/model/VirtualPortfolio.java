package com.newtrading.api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "virtual_portfolios")
public class VirtualPortfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "current_balance", nullable = false, precision = 18, scale = 8)
    private BigDecimal currentBalance = new BigDecimal("100000.00000000");

    @Column(name = "initial_balance", nullable = false, precision = 18, scale = 8)
    private BigDecimal initialBalance = new BigDecimal("100000.00000000");

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    public VirtualPortfolio() {}

    public VirtualPortfolio(User user) {
        this.user = user;
        this.currentBalance = new BigDecimal("100000.00000000");
        this.initialBalance = new BigDecimal("100000.00000000");
    }

    // Getters et Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public BigDecimal getCurrentBalance() { return currentBalance; }
    public void setCurrentBalance(BigDecimal currentBalance) { this.currentBalance = currentBalance; }

    public BigDecimal getInitialBalance() { return initialBalance; }
    public void setInitialBalance(BigDecimal initialBalance) { this.initialBalance = initialBalance; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}
