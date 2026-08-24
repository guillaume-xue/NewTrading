package com.newtrading.api.controller;

import com.newtrading.api.dto.portfolio.response.PortfolioResponse;
import com.newtrading.api.service.VirtualPortfolioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/portfolio")
public class VirtualPortfolioController {

    private final VirtualPortfolioService portfolioService;

    public VirtualPortfolioController(VirtualPortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping
    public ResponseEntity<PortfolioResponse> getMyPortfolio(@AuthenticationPrincipal UUID userId) {
        PortfolioResponse response = portfolioService.getPortfolioByUserId(userId);
        return ResponseEntity.ok(response);
    }
}
