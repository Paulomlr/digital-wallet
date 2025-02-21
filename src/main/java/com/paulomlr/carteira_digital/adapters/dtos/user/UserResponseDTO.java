package com.paulomlr.carteira_digital.adapters.dtos.user;

import com.paulomlr.carteira_digital.core.domain.User;

import java.util.UUID;

public record UserResponseDTO(UUID userID) {
    public UserResponseDTO(User user) {
        this(user.getUserId());
    }
}
