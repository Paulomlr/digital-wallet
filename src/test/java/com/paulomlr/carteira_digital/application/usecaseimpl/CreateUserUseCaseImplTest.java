package com.paulomlr.carteira_digital.application.usecaseimpl;

import com.paulomlr.carteira_digital.application.gateways.UserRepository;
import com.paulomlr.carteira_digital.application.usecaseimpl.exceptions.EmailAlreadyExistsException;
import com.paulomlr.carteira_digital.core.domain.User;
import com.paulomlr.carteira_digital.core.domain.Wallet;
import com.paulomlr.carteira_digital.core.ports.PasswordEncoder;
import com.paulomlr.carteira_digital.core.usecase.CreateWalletUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateUserUseCaseImplTest {

    private static final String WALLET_CODE = "1111222233334444";

    private UserRepository userRepository;
    private CreateWalletUseCase createWalletUseCase;
    private CreateUserUseCaseImpl createUserUseCase;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        createWalletUseCase = Mockito.mock(CreateWalletUseCase.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        createUserUseCase = new CreateUserUseCaseImpl(userRepository, createWalletUseCase, passwordEncoder);
    }

    @Test
    @DisplayName("Should create a user when email does not exist")
    void shouldCreateUserWhenEmailDoesNotExist() {
        String name = "User";
        String email = "user@example.com";
        String password = "secureP@ss1";
        String encodedPassword = "encodedSecureP@ss1";
        Wallet wallet = new Wallet(WALLET_CODE);

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(createWalletUseCase.create()).thenReturn(wallet);
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
        when(userRepository.save(any(User.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        User createdUser = createUserUseCase.create(name, email, password);

        assertNotNull(createdUser);
        assertEquals(name, createdUser.getName());
        assertEquals(email, createdUser.getEmail());
        assertEquals(encodedPassword, createdUser.getPassword());
        assertEquals(wallet, createdUser.getWallet());

        verify(userRepository).findByEmail(email);
        verify(createWalletUseCase).create();
        verify(passwordEncoder).encode(password);
        verify(userRepository).save(any(User.class));
    }

    @Test
    @DisplayName("Should throw exception when email already exists")
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        String email = "user@example.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(new User("User", email, "Password1@", new Wallet(WALLET_CODE))));

        assertThrows(EmailAlreadyExistsException.class, () -> createUserUseCase.create("Paulo", email, "securePass"));

        verify(userRepository).findByEmail(email);
        verifyNoInteractions(createWalletUseCase);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    @DisplayName("Should create a wallet for a new user")
    void shouldCreateWalletForNewUser() {
        String email = "user@example.com";
        String password = "Aassw@rd123";
        String encodedPassword = "encodedAassw@rd123";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(createWalletUseCase.create()).thenReturn(new Wallet(WALLET_CODE));
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
        when(userRepository.save(any(User.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        createUserUseCase.create("New User", email, "Aassw@rd123");

        verify(createWalletUseCase, times(1)).create();
        verify(passwordEncoder).encode(password);
    }
}