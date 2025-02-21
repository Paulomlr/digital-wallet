package com.paulomlr.carteira_digital.adapters.dtos.transfer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransferRequestDTO (@NotNull @Positive BigDecimal value,
                                  @NotNull String destinationWalletCode) {

}