package com.paulomlr.carteira_digital.infra.services;

import com.paulomlr.carteira_digital.adapters.dtos.user.LoginRequestDTO;
import com.paulomlr.carteira_digital.adapters.dtos.user.LoginResponseDTO;
import com.paulomlr.carteira_digital.adapters.dtos.user.UserRequestDTO;
import com.paulomlr.carteira_digital.adapters.dtos.user.UserResponseDTO;
import com.paulomlr.carteira_digital.core.domain.User;
import com.paulomlr.carteira_digital.core.usecase.CreateUserUseCase;
import com.paulomlr.carteira_digital.core.usecase.LoginUseCase;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final CreateUserUseCase createUserUseCase;
    private final LoginUseCase loginUseCase;
    private final JwtDecoder jwtDecoder;

    public UserService(CreateUserUseCase createUserUseCase, LoginUseCase loginUseCase, JwtDecoder jwtDecoder) {
        this.createUserUseCase = createUserUseCase;
        this.loginUseCase = loginUseCase;
        this.jwtDecoder = jwtDecoder;
    }

    public UserResponseDTO createUser(UserRequestDTO userDTO) {
        User user = createUserUseCase.create(userDTO.name(), userDTO.email(), userDTO.password());
        return new UserResponseDTO(user.getUserId());
    }

    public LoginResponseDTO login(LoginRequestDTO login) {
        String token = loginUseCase.login(login.email(), login.password());
        String userId = extractUserIdFromToken(token);
        return new LoginResponseDTO(userId, token);
    }

    private String extractUserIdFromToken(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        return jwt.getSubject(); // Aqui o subject é o userId
    }
}