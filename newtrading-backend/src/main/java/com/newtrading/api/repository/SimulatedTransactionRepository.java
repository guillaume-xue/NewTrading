package com.newtrading.api.repository;

import com.newtrading.api.model.SimulatedTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface SimulatedTransactionRepository extends JpaRepository<SimulatedTransaction, UUID> {

    // Utilise automatiquement l'index `idx_transactions_portfolio_id`
    List<SimulatedTransaction> findByPortfolioIdOrderByExecutedAtDesc(UUID portfolioId);

    // Utilise l'index `idx_transactions_asset_code`
    List<SimulatedTransaction> findByAssetCode(String assetCode);
}
