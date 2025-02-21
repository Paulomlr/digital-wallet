package com.paulomlr.carteira_digital.application.usecaseimpl;

import com.paulomlr.carteira_digital.application.gateways.TransferRepository;
import com.paulomlr.carteira_digital.application.gateways.UserRepository;
import com.paulomlr.carteira_digital.application.gateways.WalletRepository;
import com.paulomlr.carteira_digital.application.usecaseimpl.exceptions.SameWalletTransferException;
import com.paulomlr.carteira_digital.application.usecaseimpl.exceptions.UserNotFoundException;
import com.paulomlr.carteira_digital.application.usecaseimpl.exceptions.WalletNotFoundException;
import com.paulomlr.carteira_digital.core.domain.Transfer;
import com.paulomlr.carteira_digital.core.domain.User;
import com.paulomlr.carteira_digital.core.domain.Wallet;
import com.paulomlr.carteira_digital.core.usecase.CreateTransferUseCase;
import com.paulomlr.carteira_digital.core.usecase.ExecuteTransferUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateTransferUseCaseImplTest {
    private static final String ORIGIN_WALLET_CODE = "1111222233334444";
    private static final String DESTINATION_WALLET_CODE = "4444333322221111";

    private TransferRepository transferRepository;
    private WalletRepository walletRepository;
    private ExecuteTransferUseCase executeTransferUseCase;
    private CreateTransferUseCase createTransferUseCase;
    private UserRepository userRepository;

    private UUID userId;
    private String destinationWalletId;

    private User user;
    private Wallet originWallet;
    private Wallet destinationWallet;

    private BigDecimal value;

    @BeforeEach
    void setUp() {
        transferRepository = mock(TransferRepository.class);
        walletRepository = mock(WalletRepository.class);
        executeTransferUseCase = mock(ExecuteTransferUseCase.class);
        userRepository = mock(UserRepository.class);
        createTransferUseCase = new CreateTransferUseCaseImpl(transferRepository, walletRepository, executeTransferUseCase, userRepository);

        userId = UUID.randomUUID();
        destinationWalletId = DESTINATION_WALLET_CODE;

        originWallet = new Wallet(ORIGIN_WALLET_CODE);
        originWallet.addBalance(new BigDecimal("1000"));

        destinationWallet = new Wallet(DESTINATION_WALLET_CODE);
        destinationWallet.addBalance(new BigDecimal("500"));

        originWallet.setWalletId(UUID.randomUUID());
        destinationWallet.setWalletId(UUID.randomUUID());

        user = new User("John Doe", "john@example.com", "Password@1", originWallet);
        value = new BigDecimal("100");
    }

    @Test
    @DisplayName("Should throw exception when user is not found")
    void shouldCreateThrowsException_WhenUserNotFound() {
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> createTransferUseCase.create(value, userId, destinationWalletId));
    }

    @Test
    @DisplayName("Should throw exception when destination wallet is not found")
    void shouldCreateThrowsException_WhenDestinationWalletNotFound() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(walletRepository.findByWalletCode(destinationWalletId)).thenReturn(Optional.empty());

        assertThrows(WalletNotFoundException.class, () -> createTransferUseCase.create(value, userId, destinationWalletId) );
    }

    @Test
    @DisplayName("Should create a successful transfer and save it")
    void shouldCreateSuccessfulTransfer_CreatesTransfer() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(walletRepository.findByWalletCode(destinationWalletId)).thenReturn(Optional.of(destinationWallet));

        Transfer transferMock = new Transfer(value, originWallet, destinationWallet);
        when(transferRepository.save(any(Transfer.class))).thenReturn(transferMock);

        doNothing().when(executeTransferUseCase).process(any(BigDecimal.class), eq(originWallet), eq(destinationWallet));

        Transfer transfer = createTransferUseCase.create(value, userId, destinationWalletId);

        assertNotNull(transfer);
        assertEquals(value, transfer.getAmount());
        assertEquals(originWallet, transfer.getOriginWallet());
        assertEquals(destinationWallet, transfer.getDestinationWallet());

        verify(transferRepository, times(1)).save(any(Transfer.class));
    }

    @Test
    @DisplayName("Should throw exception when trying to transfer to the same wallet")
    void shouldThrowException_WhenTransferringToSameWallet() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(walletRepository.findByWalletCode(originWallet.getWalletCode())).thenReturn(Optional.of(originWallet));

        assertThrows(SameWalletTransferException.class, () ->
                createTransferUseCase.create(value, userId, ORIGIN_WALLET_CODE)
        );
    }
}