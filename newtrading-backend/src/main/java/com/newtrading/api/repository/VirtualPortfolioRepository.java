package com.newtrading.api.repository;

import com.newtrading.api.model.VirtualPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VirtualPortfolioRepository extends JpaRepository<VirtualPortfolio, UUID> {

    // Récupérer le portefeuille via l'UUID de l'utilisateur connecté
    Optional<VirtualPortfolio> findByUserId(UUID userId);
}
