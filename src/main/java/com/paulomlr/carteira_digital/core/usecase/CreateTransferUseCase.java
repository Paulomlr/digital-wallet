package com.paulomlr.carteira_digital.core.usecase;

import com.paulomlr.carteira_digital.core.domain.Transfer;

import java.math.BigDecimal;
import java.util.UUID;

public interface CreateTransferUseCase {

    Transfer create(BigDecimal value, UUID userId, String destinationWalletCode);
}
