package com.paulomlr.carteira_digital.core.domain;

import com.paulomlr.carteira_digital.core.domain.exceptions.InsufficientBalanceException;
import com.paulomlr.carteira_digital.core.domain.exceptions.InvalidTransactionAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {

    private static final BigDecimal AMOUNT_100 = BigDecimal.valueOf(100);
    private static final BigDecimal AMOUNT_0 = BigDecimal.valueOf(0);
    private static final BigDecimal AMOUNT_NEGATIVE = BigDecimal.valueOf(-100);

    private Wallet wallet;

    @BeforeEach
    void setUp() {
        String walletCode = "1111222233334444";
        wallet = new Wallet(walletCode);
    }

    @Test
    @DisplayName("Should start with zero balance")
    void shouldStartWithZeroBalance() {
        assertEquals(AMOUNT_0, wallet.getBalance());
    }

    @Test
    @DisplayName("Should add amount successfully")
    void shouldAddAmountSuccessfully() {
        wallet.addBalance(AMOUNT_100);
        assertEquals(AMOUNT_100, wallet.getBalance());
    }

    @Test
    @DisplayName("Should throw exception when adding negative or zero amount")
    void shouldThrowExceptionWhenAddingNegativeOrZeroAmount() {
        assertThrows(InvalidTransactionAmountException.class, () -> wallet.addBalance(AMOUNT_0));
        assertThrows(InvalidTransactionAmountException.class, () -> wallet.addBalance(AMOUNT_NEGATIVE));
    }

    @Test
    @DisplayName("Should subtract amount successfully")
    void shouldSubtractAmountSuccessfully() {
        wallet.addBalance(AMOUNT_100);
        wallet.subtractBalance(AMOUNT_100);
        assertEquals(AMOUNT_0, wallet.getBalance());
    }

    @Test
    @DisplayName("Should throw exception when trying to subtract more than balance")
    void shouldThrowExceptionWhenTryingToSubtractMoreThanBalance() {
        assertThrows(InsufficientBalanceException.class, () -> wallet.subtractBalance(AMOUNT_100));
    }

    @Test
    @DisplayName("Should throw exception when trying to subtract null amount")
    void shouldThrowExceptionWhenTryingToSubtractNullAmount() {
        assertThrows(InvalidTransactionAmountException.class, () -> wallet.subtractBalance(null));
    }

    @Test
    @DisplayName("Should throw exception when trying to subtract negative or zero amount")
    void shouldThrowExceptionWhenTryingToSubtractNegativeOrZeroAmount() {
        assertThrows(InvalidTransactionAmountException.class, () -> wallet.subtractBalance(AMOUNT_NEGATIVE));
        assertThrows(InvalidTransactionAmountException.class, () -> wallet.subtractBalance(AMOUNT_0));
    }
}