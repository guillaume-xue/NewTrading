package com.newtrading.api.controller;

import com.newtrading.api.model.SimulatedTransaction;
import com.newtrading.api.service.TradingService;
import jakarta.validation.constraints.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final TradingService tradingService;

    public OrderController(TradingService tradingService) {
        this.tradingService = tradingService;
    }

    public record OrderRequest(
        @NotNull UUID portfolioId,
        @NotBlank String assetCode,
        @Pattern(regexp = "BUY|SELL") String direction,
        @Positive BigDecimal quantity,
        @Positive BigDecimal price
    ) {}

    @PostMapping
    public ResponseEntity<SimulatedTransaction> placeOrder(@RequestBody OrderRequest request) {
        SimulatedTransaction result = tradingService.executeOrder(
            request.portfolioId(),
            request.assetCode(),
            request.direction(),
            request.quantity(),
            request.price()
        );
        return ResponseEntity.ok(result);
    }
}
