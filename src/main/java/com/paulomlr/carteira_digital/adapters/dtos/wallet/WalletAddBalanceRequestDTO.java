package com.paulomlr.carteira_digital.adapters.dtos.wallet;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record WalletAddBalanceRequestDTO(@Positive BigDecimal value) {
}
