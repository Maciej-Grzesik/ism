package org.dreamsellers.error;

public class InvalidTokenOrPasswordException extends RuntimeException {
    public InvalidTokenOrPasswordException(String msg) {
        super(msg);
    }
}
