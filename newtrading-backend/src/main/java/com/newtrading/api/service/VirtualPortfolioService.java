package com.newtrading.api.service;

import com.newtrading.api.dto.portfolio.response.PortfolioResponse;
import com.newtrading.api.model.VirtualPortfolio;
import com.newtrading.api.repository.VirtualPortfolioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class VirtualPortfolioService {

    private final VirtualPortfolioRepository portfolioRepository;

    public VirtualPortfolioService(VirtualPortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    @Transactional(readOnly = true)
    public PortfolioResponse getPortfolioByUserId(UUID userId) {
        VirtualPortfolio portfolio = portfolioRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Portefeuille introuvable pour cet utilisateur."));

        return new PortfolioResponse(
                portfolio.getId(),
                portfolio.getUser().getId(),
                portfolio.getCurrentBalance(),
                portfolio.getInitialBalance(),
                portfolio.getCreatedAt()
        );
    }
}
