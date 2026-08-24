package com.newtrading.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // 1. Origines autorisées (Frontend Web Next.js / Mobile)
        configuration.setAllowedOrigins(List.of(
            "http://localhost:3000",        // Dev Web local
            "https://app.newtrading.com"     // URL de Production
        ));

        // 2. Méthodes HTTP autorisées
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));

        // 3. En-têtes autorisés envoyés par le client (Ex: JWT Bearer Token, Content-Type)
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With", "Accept"));

        // 4. Autoriser la transmission de cookies ou de headers d'authentification
        configuration.setAllowCredentials(true);

        // 5. Durée de mise en cache de la réponse OPTIONS par le navigateur (1 heure)
        // Réduit le nombre d'appels preflight inutiles
        configuration.setMaxAge(3600L);

        // Appliquer cette configuration à toutes les routes de l'API (/**)
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
