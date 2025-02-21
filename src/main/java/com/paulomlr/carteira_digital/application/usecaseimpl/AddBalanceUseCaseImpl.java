package com.paulomlr.carteira_digital.application.usecaseimpl;

import com.paulomlr.carteira_digital.application.gateways.UserRepository;
import com.paulomlr.carteira_digital.application.gateways.WalletRepository;
import com.paulomlr.carteira_digital.application.usecaseimpl.exceptions.UserNotFoundException;
import com.paulomlr.carteira_digital.core.domain.User;
import com.paulomlr.carteira_digital.core.domain.Wallet;
import com.paulomlr.carteira_digital.core.usecase.AddBalanceUseCase;

import java.math.BigDecimal;
import java.util.UUID;

public class AddBalanceUseCaseImpl implements AddBalanceUseCase {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    public AddBalanceUseCaseImpl(UserRepository userRepository, WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }

    @Override
    public void add(UUID userId, BigDecimal value) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        Wallet wallet = user.getWallet();

        wallet.addBalance(value);

        walletRepository.save(wallet);
    }
}
