package com.newtrading.api.controller;

import com.newtrading.api.model.User;
import com.newtrading.api.service.UserService;
import jakarta.validation.constraints.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

public record UserRequest(
    @NotBlank String username,
    @Email String email,
    @NotBlank String password,
    @NotNull UUID portfolioId
) {}
