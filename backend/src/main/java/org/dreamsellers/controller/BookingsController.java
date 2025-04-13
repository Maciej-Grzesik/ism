package org.dreamsellers.controller;

import io.swagger.models.Response;
import lombok.AllArgsConstructor;
import org.api.BookingsApi;
import org.dreamsellers.service.BookingsService;
import org.model.BookingResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class BookingsController implements BookingsApi {
    private BookingsService bookingsService;

    @Override
    public ResponseEntity<List<BookingResponseDto>> bookingsUserIdGet(Integer userId) {

        List<BookingResponseDto> bookings = bookingsService.getAllBookingsByUser((long) userId);

        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookingResponseDto> bookingsBookingIdGet(Integer bookingId) {
        BookingResponseDto booking = bookingsService.getBookingByBookingId((long) bookingId);

        return new ResponseEntity<>(booking, HttpStatus.OK);
    }
}
