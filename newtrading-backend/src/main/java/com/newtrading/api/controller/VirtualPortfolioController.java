package com.newtrading.api.controller;

import com.newtrading.api.model.VirtualPortfolio;
import com.newtrading.api.repository.VirtualPortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/virtual-portfolios")
public class VirtualPortfolioController {

    @Autowired
    private VirtualPortfolioRepository virtualPortfolioRepository;

    @PostMapping
    public ResponseEntity<VirtualPortfolio> createVirtualPortfolio(@RequestBody VirtualPortfolio virtualPortfolio) {
        VirtualPortfolio createdPortfolio = virtualPortfolioRepository.save(virtualPortfolio);
        return ResponseEntity.ok(createdPortfolio);
    }
}
