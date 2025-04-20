package org.dreamsellers.errors;

public class UserNotFoundException extends RuntimeException {

    private UserNotFoundException(String message) {
        super(message);
    }

    public static UserNotFoundException create(String username) {
        return new UserNotFoundException("A user with the username " + username + "already exists");
    }
}
