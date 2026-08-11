package com.newtrading.api.controller;

import com.newtrading.api.dto.OrderRequest;
import com.newtrading.api.dto.TransactionResponse;
import com.newtrading.api.service.TradingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final TradingService tradingService;

    public OrderController(TradingService tradingService) {
        this.tradingService = tradingService;
    }

    /**
     * Placer un ordre d'achat ou de vente simulé.
     * POST /api/v1/orders
     */
    @PostMapping
    public ResponseEntity<TransactionResponse> createOrder(
            @Valid @RequestBody OrderRequest orderRequest,
            Principal principal // Récupère l'utilisateur actuellement authentifié via Spring Security
    ) {
        // En entreprise, l'ID utilisateur est extrait du SecurityContext (ex: UUID sous forme de String)
        UUID currentUserId = UUID.fromString(principal.getName());

        TransactionResponse response = tradingService.processOrder(currentUserId, orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Obtenir l'historique complet des transactions du portefeuille de l'utilisateur.
     * GET /api/v1/orders/history
     */
    @GetMapping("/history")
    public ResponseEntity<List<TransactionResponse>> getOrderHistory(Principal principal) {
        UUID currentUserId = UUID.fromString(principal.getName());
        List<TransactionResponse> history = tradingService.getPortfolioHistory(currentUserId);
        return ResponseEntity.ok(history);
    }
}
