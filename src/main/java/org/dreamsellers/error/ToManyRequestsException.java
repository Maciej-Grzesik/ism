package org.dreamsellers.error;

public class ToManyRequestsException extends RuntimeException {
    public ToManyRequestsException(String msg) {
        super(msg);
    }
}
