package com.newtrading.api.repository;

import com.newtrading.api.model.SimulatedTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SimulatedTransactionRepository extends JpaRepository<SimulatedTransaction, UUID> {

    // Historique des transactions d'un portefeuille trié par date décroissante
    List<SimulatedTransaction> findByPortfolioIdOrderByExecutedAtDesc(UUID portfolioId);
}
