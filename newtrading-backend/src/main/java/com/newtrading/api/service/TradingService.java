package com.newtrading.api.service;

import com.newtrading.api.dto.OrderRequest;
import com.newtrading.api.dto.TransactionResponse;
import com.newtrading.api.model.SimulatedTransaction;
import com.newtrading.api.model.VirtualPortfolio;
import com.newtrading.api.repository.SimulatedTransactionRepository;
import com.newtrading.api.repository.VirtualPortfolioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class TradingService {

    private final VirtualPortfolioRepository portfolioRepository;
    private final SimulatedTransactionRepository transactionRepository;

    public TradingService(VirtualPortfolioRepository portfolioRepository,
                          SimulatedTransactionRepository transactionRepository) {
        this.portfolioRepository = portfolioRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public TransactionResponse processOrder(UUID userId, OrderRequest request) {
        // 1. Récupération du portefeuille rattaché à l'utilisateur
        VirtualPortfolio portfolio = portfolioRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Portefeuille introuvable pour l'utilisateur ID: " + userId));

        BigDecimal totalCost = request.price().multiply(request.quantity());

        // 2. Traitement des règles métier selon la direction (BUY / SELL)
        if ("BUY".equalsIgnoreCase(request.direction())) {
            if (portfolio.getCurrentBalance().compareTo(totalCost) < 0) {
                throw new IllegalStateException("Solde insuffisant (" + portfolio.getCurrentBalance() + ") pour exécuter un achat de " + totalCost);
            }
            portfolio.setCurrentBalance(portfolio.getCurrentBalance().subtract(totalCost));
        } else if ("SELL".equalsIgnoreCase(request.direction())) {
            // Note: On pourrait ajouter une vérification de possession des titres ici
            portfolio.setCurrentBalance(portfolio.getCurrentBalance().add(totalCost));
        }

        portfolioRepository.save(portfolio);

        // 3. Enregistrement de la transaction
        SimulatedTransaction transaction = SimulatedTransaction.builder()
                .portfolio(portfolio)
                .assetCode(request.assetCode().toUpperCase())
                .orderDirection(request.direction().toUpperCase())
                .quantity(request.quantity())
                .executionPrice(request.price())
                .build();

        SimulatedTransaction saved = transactionRepository.save(transaction);
        return TransactionResponse.fromEntity(saved);
    }

    @Transactional(readOnly = true)
    public List<TransactionResponse> getPortfolioHistory(UUID userId) {
        VirtualPortfolio portfolio = portfolioRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Portefeuille introuvable pour l'utilisateur ID: " + userId));

        // Utilise l'index idx_transactions_portfolio_id défini en BDD
        return transactionRepository.findByPortfolioIdOrderByExecutedAtDesc(portfolio.getId())
                .stream()
                .map(TransactionResponse::fromEntity)
                .toList();
    }
}
