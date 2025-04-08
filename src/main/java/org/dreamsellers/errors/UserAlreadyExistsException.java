package org.dreamsellers.errors;

public class UserAlreadyExistsException extends RuntimeException {

    private UserAlreadyExistsException(String message) {
        super(message);
    }

    public static UserAlreadyExistsException create(String username) {
        return new UserAlreadyExistsException("A user with the username " + username + "already exists");
    }
}
