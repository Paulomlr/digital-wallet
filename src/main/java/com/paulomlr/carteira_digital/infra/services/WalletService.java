package com.paulomlr.carteira_digital.infra.services;

import com.paulomlr.carteira_digital.adapters.dtos.wallet.WalletBalanceResponseDTO;
import com.paulomlr.carteira_digital.core.usecase.AddBalanceUseCase;
import com.paulomlr.carteira_digital.core.usecase.CheckBalanceUseCase;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class WalletService {

    private final CheckBalanceUseCase checkBalanceUseCase;
    private final AddBalanceUseCase addBalanceUseCase;

    public WalletService(CheckBalanceUseCase checkBalanceUseCase, AddBalanceUseCase addBalanceUseCase) {
        this.checkBalanceUseCase = checkBalanceUseCase;
        this.addBalanceUseCase = addBalanceUseCase;
    }

    public WalletBalanceResponseDTO getBalance(UUID userId) {
        BigDecimal balance = checkBalanceUseCase.check(userId);
        return new WalletBalanceResponseDTO(balance);
    }

    public void addBalance(UUID userId, BigDecimal value) {
        addBalanceUseCase.add(userId, value);
    }
}