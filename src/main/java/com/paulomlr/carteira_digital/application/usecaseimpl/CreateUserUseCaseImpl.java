package com.paulomlr.carteira_digital.application.usecaseimpl;

import com.paulomlr.carteira_digital.application.gateways.UserRepository;
import com.paulomlr.carteira_digital.application.usecaseimpl.exceptions.EmailAlreadyExistsException;
import com.paulomlr.carteira_digital.core.domain.User;
import com.paulomlr.carteira_digital.core.domain.Wallet;
import com.paulomlr.carteira_digital.core.ports.PasswordEncoder;
import com.paulomlr.carteira_digital.core.usecase.CreateUserUseCase;
import com.paulomlr.carteira_digital.core.usecase.CreateWalletUseCase;

public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepository userRepository;
    private final CreateWalletUseCase createWalletUseCase;
    private final PasswordEncoder passwordEncoder;

    public CreateUserUseCaseImpl(UserRepository userRepository, CreateWalletUseCase createWalletUseCase, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.createWalletUseCase = createWalletUseCase;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(String name, String email, String password) {
        userRepository.findByEmail(email).ifPresent(emailExist -> {
            throw new EmailAlreadyExistsException("Email already registered!");
        });

        Wallet wallet = createWalletUseCase.create();

        User user = new User(name, email, password, wallet);
        user.setPassword(passwordEncoder.encode(password));

        return userRepository.save(user);
    }
}