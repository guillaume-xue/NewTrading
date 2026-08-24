package com.newtrading.api.service;

import com.newtrading.api.dto.auth.request.LoginRequest;
import com.newtrading.api.dto.auth.request.RegisterRequest;
import com.newtrading.api.dto.auth.response.AuthResponse;
import com.newtrading.api.model.User;
import com.newtrading.api.model.VirtualPortfolio;
import com.newtrading.api.repository.UserRepository;
import com.newtrading.api.repository.VirtualPortfolioRepository;
import com.newtrading.api.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final VirtualPortfolioRepository portfolioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(UserRepository userRepository,
                       VirtualPortfolioRepository portfolioRepository,
                       PasswordEncoder passwordEncoder,
                       JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.portfolioRepository = portfolioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Cette adresse email est déjà utilisée.");
        }

        // 1. Hashage du mot de passe et création du User
        String hashedPassword = passwordEncoder.encode(request.password());
        User user = new User(request.email(), hashedPassword);
        User savedUser = userRepository.save(user);

        // 2. Initialisation automatique du portefeuille virtuel (100 000 $)
        VirtualPortfolio portfolio = new VirtualPortfolio();
        portfolio.setUser(savedUser);
        portfolio.setCurrentBalance(new BigDecimal("100000.00000000"));
        portfolio.setInitialBalance(new BigDecimal("100000.00000000"));
        portfolioRepository.save(portfolio);

        // 3. Génération du JWT
        String token = jwtTokenProvider.generateToken(savedUser.getId(), savedUser.getEmail());
        return new AuthResponse(token, jwtTokenProvider.getExpirationDuration());
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("Identifiants incorrects."));

        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new IllegalArgumentException("Identifiants incorrects.");
        }

        String token = jwtTokenProvider.generateToken(user.getId(), user.getEmail());
        return new AuthResponse(token, jwtTokenProvider.getExpirationDuration());
    }
}
