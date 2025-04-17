package org.dreamsellers.error;

public class BusinessNotFoundException extends RuntimeException {
    public BusinessNotFoundException(String msg) {
        super(msg);
    }
}
