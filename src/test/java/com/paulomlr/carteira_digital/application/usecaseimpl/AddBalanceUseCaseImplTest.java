package com.paulomlr.carteira_digital.application.usecaseimpl;

import com.paulomlr.carteira_digital.application.gateways.UserRepository;
import com.paulomlr.carteira_digital.application.gateways.WalletRepository;
import com.paulomlr.carteira_digital.application.usecaseimpl.exceptions.UserNotFoundException;
import com.paulomlr.carteira_digital.core.domain.User;
import com.paulomlr.carteira_digital.core.domain.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddBalanceUseCaseImplTest {

    private UserRepository userRepository;
    private WalletRepository walletRepository;
    private AddBalanceUseCaseImpl addBalanceUseCase;

    private UUID userId;
    private BigDecimal value;
    private User user;
    private Wallet wallet;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        walletRepository = Mockito.mock(WalletRepository.class);
        addBalanceUseCase = new AddBalanceUseCaseImpl(userRepository, walletRepository);
        userId = UUID.randomUUID();
        value = new BigDecimal("100.00");

        String walletCode = "1111222233334444";
        wallet = new Wallet(walletCode);

        user = new User("Valid User", "valid.user@gmail.com", "Password@1", wallet);
    }

    @Test
    @DisplayName("Should add balance successfully when user exists")
    void shouldAddBalanceSuccessfully() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        addBalanceUseCase.add(userId, value);

        verify(userRepository).findById(userId);
        verify(walletRepository).save(wallet);
    }

    @Test
    @DisplayName("Should throw UserNotFoundException when user does not exist")
    void shouldThrowUserNotFoundExceptionWhenUserDoesNotExist() {
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> addBalanceUseCase.add(userId, value));

        verify(userRepository).findById(userId);
        verify(walletRepository, never()).save(any());
    }
}