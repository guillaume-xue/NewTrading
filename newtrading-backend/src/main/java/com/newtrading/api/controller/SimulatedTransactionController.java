package com.newtrading.api.controller;

import com.newtrading.api.model.SimulatedTransaction;
import com.newtrading.api.service.TradingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/simulated-transactions")
public class SimulatedTransactionController {
    private final TradingService tradingService;

    public SimulatedTransactionController(TradingService tradingService) {
        this.tradingService = tradingService;
    }

    @PostMapping
    public ResponseEntity<SimulatedTransaction> createSimulatedTransaction(@Valid @RequestBody SimulatedTransaction transaction) {
        SimulatedTransaction createdTransaction = tradingService.createSimulatedTransaction(transaction);
        return ResponseEntity.ok(createdTransaction);
    }
}
