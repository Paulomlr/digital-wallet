package com.paulomlr.carteira_digital.core.domain;

import jakarta.validation.ValidationException;

import java.util.Objects;
import java.util.UUID;

public class User {

    private UUID userId;
    private String name;
    private String email;
    private String password;
    private Wallet wallet;

    public User(UUID userId, String name, String email, String password, Wallet wallet) {
        validateName(name);
        validateEmail(email);
        validatePassword(password);
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.wallet = wallet;
    }

    public User(String name, String email, String password, Wallet wallet) {
        validateName(name);
        validateEmail(email);
        validatePassword(password);
        this.name = name;
        this.email = email;
        this.password = password;
        this.wallet = wallet;
    }

    public User() {
    }

    public UUID getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        validateEmail(email);
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        validatePassword(password);
        this.password = password;
    }

    public Wallet getWallet() {
        return wallet;
    }

    private boolean isValidName(String name) {
        return name.matches("^[A-Za-zÀ-ÖØ-öø-ÿ' -]{3,50}$");
    }

    private void validateName(String name) {
        if(!isValidName(name)) {
            throw new ValidationException("Invalid name");
        }
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    private void validateEmail(String email) {
        if(!isValidEmail(email)) {
            throw new ValidationException("Invalid email");
        }
    }

    private boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*\\W).{8,}$");
    }

    private void validatePassword(String password) {
        if (!isValidPassword(password)) {
            throw new ValidationException("Invalid password");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId);
    }
}