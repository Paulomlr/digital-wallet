package com.paulomlr.carteira_digital.application.usecaseimpl;

import com.paulomlr.carteira_digital.application.gateways.WalletRepository;
import com.paulomlr.carteira_digital.core.domain.Wallet;
import com.paulomlr.carteira_digital.core.usecase.ExecuteTransferUseCase;

import java.math.BigDecimal;

public class ExecuteTransferUseCaseImpl implements ExecuteTransferUseCase {

    private final WalletRepository walletRepository;

    public ExecuteTransferUseCaseImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public void process(BigDecimal value, Wallet originWallet, Wallet destinationWallet) {
        originWallet.subtractBalance(value);
        destinationWallet.addBalance(value);
        walletRepository.save(originWallet);
        walletRepository.save(destinationWallet);
    }
}