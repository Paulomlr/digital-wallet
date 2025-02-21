package com.paulomlr.carteira_digital.core.usecase;

import com.paulomlr.carteira_digital.core.domain.Wallet;

import java.math.BigDecimal;

public interface ExecuteTransferUseCase {

    void process(BigDecimal value, Wallet originWallet, Wallet destinationWallet);
}
