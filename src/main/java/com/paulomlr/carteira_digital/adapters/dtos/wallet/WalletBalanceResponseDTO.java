package com.paulomlr.carteira_digital.adapters.dtos.wallet;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public record WalletBalanceResponseDTO(BigDecimal balance) {

    @JsonProperty("balance")
    public String amount() {
        return formatAmount(balance);
    }

    private static String formatAmount(BigDecimal amount) {
        return NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR")).format(amount);
    }
}
