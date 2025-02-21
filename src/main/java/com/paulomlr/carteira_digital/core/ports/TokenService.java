package com.paulomlr.carteira_digital.core.ports;

import com.paulomlr.carteira_digital.core.domain.User;

public interface TokenService {

    String generateToken(User user);
    boolean validateToken(String token);
}
