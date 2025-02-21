package com.paulomlr.carteira_digital.core.usecase;

import com.paulomlr.carteira_digital.core.domain.User;

public interface CreateUserUseCase {

    User create(String name, String email, String password);
}
