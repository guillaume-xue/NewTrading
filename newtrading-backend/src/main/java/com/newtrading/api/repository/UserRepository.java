package com.newtrading.api.repository;

import com.newtrading.api.model.SimulatedTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.newtrading.api.model.User;
import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    // Recherche par email pour le login classique et le chargement Spring Security
    Optional<User> findByEmail(String email);

    // Vérifie si un compte existe déjà lors de l'inscription
    boolean existsByEmail(String email);

    // Recherche par identifiant OAuth pour la connexion Google OAuth2
    Optional<User> findByOauthId(String oauthId);
}
