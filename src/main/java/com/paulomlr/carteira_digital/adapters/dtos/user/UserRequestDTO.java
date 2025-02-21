package com.paulomlr.carteira_digital.adapters.dtos.user;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(@NotBlank String name,
                             @NotBlank String email,
                             @NotBlank String password) {
}
