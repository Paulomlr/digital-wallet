package com.paulomlr.carteira_digital.application.usecaseimpl;

import com.paulomlr.carteira_digital.application.gateways.UserRepository;
import com.paulomlr.carteira_digital.application.usecaseimpl.exceptions.InvalidCredentialsException;
import com.paulomlr.carteira_digital.application.usecaseimpl.exceptions.UserNotFoundException;
import com.paulomlr.carteira_digital.core.domain.User;
import com.paulomlr.carteira_digital.core.usecase.LoginUseCase;
import com.paulomlr.carteira_digital.core.ports.PasswordEncoder;
import com.paulomlr.carteira_digital.core.ports.TokenService;

public class LoginUseCaseImpl implements LoginUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public LoginUseCaseImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Override
    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Invalid password!");
        }

        return tokenService.generateToken(user);
    }
}
