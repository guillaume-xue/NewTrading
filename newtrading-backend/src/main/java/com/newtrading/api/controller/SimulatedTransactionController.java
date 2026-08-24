package com.newtrading.api.controller;

import com.newtrading.api.dto.transaction.request.CreateOrderRequest;
import com.newtrading.api.dto.transaction.response.TransactionResponse;
import com.newtrading.api.service.SimulatedTransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
public class SimulatedTransactionController {

    private final SimulatedTransactionService transactionService;

    public SimulatedTransactionController(SimulatedTransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> createOrder(
            @AuthenticationPrincipal UUID userId,
            @Valid @RequestBody CreateOrderRequest request) {

        TransactionResponse response = transactionService.executeOrder(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
