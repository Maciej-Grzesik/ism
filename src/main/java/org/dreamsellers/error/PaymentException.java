package org.dreamsellers.error;

public class PaymentException extends RuntimeException {
    public PaymentException(String msg) {
        super(msg);
    }
}
