package com.newtrading.api.dto.transaction.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateOrderRequest(

    @NotBlank(message = "Le symbole de l'actif est obligatoire")
    @Size(max = 12, message = "Le symbole de l'actif ne doit pas dépasser 12 caractères")
    String assetCode,

    @NotBlank(message = "Le sens de l'ordre est obligatoire")
    @Pattern(regexp = "^(BUY|SELL)$", message = "Le type d'ordre doit être 'BUY' ou 'SELL'")
    String orderDirection,

    @NotNull(message = "La quantité est obligatoire")
    @DecimalMin(value = "0.00000001", message = "La quantité doit être strictement positive")
    BigDecimal quantity,

    @NotNull(message = "Le prix d'exécution est obligatoire")
    @DecimalMin(value = "0.00000001", message = "Le prix doit être strictement positif")
    BigDecimal executionPrice

) {}
