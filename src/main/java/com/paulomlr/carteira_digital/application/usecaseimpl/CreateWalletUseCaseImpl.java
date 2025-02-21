package com.paulomlr.carteira_digital.application.usecaseimpl;

import com.paulomlr.carteira_digital.application.gateways.WalletRepository;
import com.paulomlr.carteira_digital.core.domain.Wallet;
import com.paulomlr.carteira_digital.core.ports.WalletCodeUtils;
import com.paulomlr.carteira_digital.core.usecase.CreateWalletUseCase;

public class CreateWalletUseCaseImpl implements CreateWalletUseCase {

    private final WalletRepository walletRepository;

    public CreateWalletUseCaseImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet create() {
        String walletCode;

        do {
            walletCode = WalletCodeUtils.generateCode();
        } while (walletRepository.existsByWalletCode(walletCode));

        return new Wallet(walletCode);
    }
}
