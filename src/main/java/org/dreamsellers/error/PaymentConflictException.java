package org.dreamsellers.error;

public class PaymentConflictException extends RuntimeException {
    public PaymentConflictException(String msg) {
        super(msg);
    }
}
