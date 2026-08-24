package com.newtrading.api.service;

import com.newtrading.api.dto.transaction.request.CreateOrderRequest;
import com.newtrading.api.dto.transaction.response.TransactionResponse;
import com.newtrading.api.model.SimulatedTransaction;
import com.newtrading.api.model.VirtualPortfolio;
import com.newtrading.api.repository.SimulatedTransactionRepository;
import com.newtrading.api.repository.VirtualPortfolioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class SimulatedTransactionService {

    private final SimulatedTransactionRepository transactionRepository;
    private final VirtualPortfolioRepository portfolioRepository;

    public SimulatedTransactionService(SimulatedTransactionRepository transactionRepository,
                                       VirtualPortfolioRepository portfolioRepository) {
        this.transactionRepository = transactionRepository;
        this.portfolioRepository = portfolioRepository;
    }

    @Transactional
    public TransactionResponse executeOrder(UUID userId, CreateOrderRequest request) {
        // 1. Récupération du portefeuille de l'utilisateur
        VirtualPortfolio portfolio = portfolioRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Portefeuille introuvable pour cet utilisateur."));

        BigDecimal totalCost = request.quantity().multiply(request.executionPrice());

        // 2. Traitement selon le sens de l'ordre
        if ("BUY".equalsIgnoreCase(request.orderDirection())) {
            if (portfolio.getCurrentBalance().compareTo(totalCost) < 0) {
                throw new IllegalArgumentException("Solde insuffisant pour exécuter cet ordre d'achat.");
            }
            // Déduction du montant
            portfolio.setCurrentBalance(portfolio.getCurrentBalance().subtract(totalCost));
        } else if ("SELL".equalsIgnoreCase(request.orderDirection())) {
            // Crédit du montant sur le solde
            portfolio.setCurrentBalance(portfolio.getCurrentBalance().add(totalCost));
        }

        portfolioRepository.save(portfolio);

        // 3. Enregistrement de la transaction
        SimulatedTransaction transaction = new SimulatedTransaction(
                portfolio,
                request.assetCode().toUpperCase(),
                request.orderDirection().toUpperCase(),
                request.quantity(),
                request.executionPrice()
        );

        SimulatedTransaction savedTx = transactionRepository.save(transaction);

        return new TransactionResponse(
                savedTx.getId(),
                portfolio.getId(),
                savedTx.getAssetCode(),
                savedTx.getOrderDirection(),
                savedTx.getQuantity(),
                savedTx.getExecutionPrice(),
                totalCost,
                savedTx.getExecutedAt()
        );
    }
}
