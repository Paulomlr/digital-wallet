package com.paulomlr.carteira_digital.application.usecaseimpl;

import com.paulomlr.carteira_digital.application.gateways.UserRepository;
import com.paulomlr.carteira_digital.application.usecaseimpl.exceptions.UserNotFoundException;
import com.paulomlr.carteira_digital.core.domain.User;
import com.paulomlr.carteira_digital.core.domain.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CheckBalanceUseCaseImplTest {
    private static final String WALLET_CODE = "1111222233334444";

    private UserRepository userRepository;
    private CheckBalanceUseCaseImpl checkBalanceUseCase;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        checkBalanceUseCase = new CheckBalanceUseCaseImpl(userRepository);
    }

    @Test
    @DisplayName("Should return balance when user exists")
    void shouldReturnBalanceWhenUserExists() {
        UUID userId = UUID.randomUUID();
        BigDecimal expectedBalance = new BigDecimal("150.00");
        Wallet wallet = new Wallet(WALLET_CODE);
        wallet.addBalance(expectedBalance);
        User user = new User("Valid user", "valid.user@gmail.com", "Password@1", wallet);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        BigDecimal actualBalance = checkBalanceUseCase.check(userId);

        assertEquals(expectedBalance, actualBalance);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    @DisplayName("Should throw exception when user is not found")
    void shouldThrowExceptionWhenUserNotFound() {
        UUID userId = UUID.randomUUID();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> checkBalanceUseCase.check(userId));

        assertEquals("User not found!", exception.getMessage());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    @DisplayName("Should return zero when user balance is zero")
    void shouldReturnZeroWhenUserBalanceIsZero() {
        UUID userId = UUID.randomUUID();
        BigDecimal expectedBalance = BigDecimal.ZERO;
        Wallet wallet = new Wallet(WALLET_CODE);
        User user = new User("Valid user", "valid.user@gmail.com", "Password@1", wallet);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        BigDecimal actualBalance = checkBalanceUseCase.check(userId);

        assertEquals(expectedBalance, actualBalance);
        verify(userRepository, times(1)).findById(userId);
    }
}