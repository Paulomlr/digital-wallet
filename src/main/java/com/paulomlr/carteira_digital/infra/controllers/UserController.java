package com.paulomlr.carteira_digital.infra.controllers;

import com.paulomlr.carteira_digital.adapters.dtos.user.LoginRequestDTO;
import com.paulomlr.carteira_digital.adapters.dtos.user.LoginResponseDTO;
import com.paulomlr.carteira_digital.adapters.dtos.user.UserRequestDTO;
import com.paulomlr.carteira_digital.adapters.dtos.user.UserResponseDTO;
import com.paulomlr.carteira_digital.infra.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO login) {
        LoginResponseDTO loginResponse = userService.login(login);

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping
    public ResponseEntity<Void> createAccount(@RequestBody @Valid UserRequestDTO userRequest) {
        UserResponseDTO userResponse = userService.createUser(userRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userResponse.userID())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
