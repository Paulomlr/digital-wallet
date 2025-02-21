package com.paulomlr.carteira_digital.core.ports;

public interface PasswordEncoder {
    String encode(String password);
    boolean matches(String rawPassword, String encodedPassword);
}
