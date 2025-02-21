package com.paulomlr.carteira_digital.core.domain;

import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private Wallet wallet;
    private String validName;
    private String validEmail;
    private String validPassword;

    private String invalidName;
    private String invalidEmail;
    private String invalidPassword;

    @BeforeEach
    void setUp() {
        String walletCode = "1111222233334444";
        wallet = new Wallet(walletCode);
        validName = "Valid Name";
        validEmail = "valid.email@gmail.com";
        validPassword = "ValidPassword1!";

        invalidName = "N@";
        invalidEmail = "invalid.email@.com";
        invalidPassword = "pass";
    }

    @Test
    @DisplayName("Should create a user successfully with valid data")
    void shouldCreateUserSuccessfully() {
        assertDoesNotThrow(() -> new User(validName, validEmail, validPassword, wallet));
    }

    @Test
    @DisplayName("Should throw exception when creating a user with an invalid name")
    void shouldThrowExceptionWhenCreatingUserWithInvalidName() {
        assertThrows(ValidationException.class, () -> new User(invalidName, validEmail, validPassword, wallet));
    }

    @Test
    @DisplayName("Should throw exception when creating a user with an invalid email")
    void shouldThrowExceptionWhenCreatingUserWithInvalidEmail() {
        assertThrows(ValidationException.class, () -> new User(validName, invalidEmail, validPassword, wallet));
    }

    @Test
    @DisplayName("Should throw exception when creating a user with an invalid password")
    void shouldThrowExceptionWhenCreatingUserWithInvalidPassword() {
        assertThrows(ValidationException.class, () -> new User(validName, validEmail, invalidPassword, wallet));
    }

    @Test
    @DisplayName("Should update the user's name successfully")
    void shouldUpdateNameSuccessfully() {
        User user = new User(validName, validEmail, validPassword, wallet);
        String newName = "Updated Name";
        user.setName(newName);
        assertEquals(newName, user.getName());
    }

    @Test
    @DisplayName("Should throw exception when updating user with an invalid name")
    void shouldThrowExceptionWhenUpdatingNameWithInvalidValue() {
        User user = new User(validName, validEmail, validPassword, wallet);
        assertThrows(ValidationException.class, () -> user.setName(invalidName));
    }

    @Test
    @DisplayName("Should update the user's email successfully")
    void shouldUpdateEmailSuccessfully() {
        User user = new User(validName, validEmail, validPassword, wallet);
        String newEmail = "new.email@gmail.com";
        user.setEmail(newEmail);
        assertEquals(newEmail, user.getEmail());
    }

    @Test
    @DisplayName("Should throw exception when updating user with an invalid email")
    void shouldThrowExceptionWhenUpdatingEmailWithInvalidValue() {
        User user = new User(validName, validEmail, validPassword, wallet);
        assertThrows(ValidationException.class, () -> user.setEmail(invalidEmail));
    }

    @Test
    @DisplayName("Should update the user's password successfully")
    void shouldUpdatePasswordSuccessfully() {
        User user = new User(validName, validEmail, validPassword, wallet);
        String newPassword = "NewPassword1!";
        user.setPassword(newPassword);
        assertEquals(newPassword, user.getPassword());
    }

    @Test
    @DisplayName("Should throw exception when updating user with an invalid password")
    void shouldThrowExceptionWhenUpdatingPasswordWithInvalidValue() {
        User user = new User(validName, validEmail, validPassword, wallet);
        assertThrows(ValidationException.class, () -> user.setPassword(invalidPassword));
    }
}