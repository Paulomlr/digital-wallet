package com.paulomlr.carteira_digital.adapters.dtos.transfer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.paulomlr.carteira_digital.core.domain.Transfer;
import com.paulomlr.carteira_digital.core.domain.User;
import com.paulomlr.carteira_digital.core.ports.WalletCodeUtils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

public record TransferResponseDTO(UUID transferId,
                                  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime date,
                                  String amount,
                                  @JsonProperty("origin") WalletInfo origin,
                                  @JsonProperty("destination") WalletInfo destination) {

    public TransferResponseDTO (Transfer transfer, User userOrigin, User userDestination){
        this(transfer.getTransferId(),
                transfer.getTransferDate(),
                formatAmount(transfer.getAmount()),
                new WalletInfo(
                        WalletCodeUtils.formatCode(transfer.getOriginWallet().getWalletCode()),
                        userOrigin.getName()
                ),
                new WalletInfo(
                        WalletCodeUtils.formatCode(transfer.getDestinationWallet().getWalletCode()),
                        userDestination.getName()
                )
        );
    }

    private static String formatAmount(BigDecimal amount) {
        return NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR")).format(amount);
    }

    public record WalletInfo(
            @JsonProperty("walletCode") String walletCode,
            @JsonProperty("ownerName") String ownerName
    ) {}
}
