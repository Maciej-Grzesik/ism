package org.dreamsellers.errors;

public class InvalidCredentialsException extends RuntimeException {

    private InvalidCredentialsException(String message) {
        super(message);
    }

    public static InvalidCredentialsException create(String username) {
        return new InvalidCredentialsException("A user with the username " + username + "already exists");
    }
}
