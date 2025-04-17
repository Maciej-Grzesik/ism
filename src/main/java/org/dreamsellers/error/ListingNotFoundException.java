package org.dreamsellers.error;

public class ListingNotFoundException extends RuntimeException {
    public ListingNotFoundException(String msg) {
        super(msg);
    }
}
