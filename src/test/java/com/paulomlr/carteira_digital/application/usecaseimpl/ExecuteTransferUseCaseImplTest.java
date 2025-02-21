package com.paulomlr.carteira_digital.application.usecaseimpl;

import com.paulomlr.carteira_digital.application.gateways.WalletRepository;
import com.paulomlr.carteira_digital.core.domain.Wallet;
import com.paulomlr.carteira_digital.core.usecase.ExecuteTransferUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExecuteTransferUseCaseImplTest {

    private WalletRepository walletRepository;
    private ExecuteTransferUseCase executeTransferUseCase;

    private Wallet originWallet;
    private Wallet destinationWallet;

    @BeforeEach
    void setUp() {
        walletRepository = mock(WalletRepository.class);
        executeTransferUseCase = new ExecuteTransferUseCaseImpl(walletRepository);

        String originWalletCode = "1111222233334444";
        originWallet = new Wallet(originWalletCode);
        originWallet.addBalance(new BigDecimal("1000"));

        String destinationWalletCode = "4444333322221111";
        destinationWallet = new Wallet(destinationWalletCode);
        destinationWallet.addBalance(new BigDecimal("500"));
    }

    @Test
    void shouldTransferBalanceCorrectly() {
        BigDecimal transferAmount = new BigDecimal("200");

        executeTransferUseCase.process(transferAmount, originWallet, destinationWallet);

        assertEquals(new BigDecimal("800"), originWallet.getBalance());
        assertEquals(new BigDecimal("700"), destinationWallet.getBalance());
        verify(walletRepository, times(1)).save(originWallet);
        verify(walletRepository, times(1)).save(destinationWallet);
    }
}