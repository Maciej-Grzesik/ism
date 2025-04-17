package org.dreamsellers.error;

import org.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//https://www.baeldung.com/exception-handling-for-rest-with-spring

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<InvalidCredentialsError> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        InvalidCredentialsError error = new InvalidCredentialsError();
        error.setError(ex.getMessage());
        error.setCode("INVALID_CREDENTIALS");
        error.setStatus(HttpStatus.UNAUTHORIZED.value());

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserNotFoundError> handleUserNotFoundException(UserNotFoundException ex) {
        UserNotFoundError error = new UserNotFoundError();
        error.setError(ex.getMessage());
        error.setCode("USER_NOT_FOUND");
        error.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ListingNotFoundException.class)
    public ResponseEntity<ListingNotFoundError> handleListingNotFoundException(ListingNotFoundException ex) {
        ListingNotFoundError error = new ListingNotFoundError();
        error.setError(ex.getMessage());
        error.setCode("LISTING_NOT_FOUND");
        error.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessNotFoundException.class)
    public ResponseEntity<BusinessNotFoundError> handleBusinessNotFoundException(BusinessNotFoundException ex) {
        BusinessNotFoundError error = new BusinessNotFoundError();
        error.setError(ex.getMessage());
        error.setCode("BUSINESS_NOT_FOUND");
        error.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<BookingNotFoundError> handleBookingNotFoundException(BookingNotFoundException ex) {
        BookingNotFoundError error = new BookingNotFoundError();
        error.setError(ex.getMessage());
        error.setCode("BOOKING_NOT_FOUND");
        error.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
