package com.paulomlr.carteira_digital.adapters.dtos.user;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(@NotBlank String email,
                              @NotBlank String password) {
}
