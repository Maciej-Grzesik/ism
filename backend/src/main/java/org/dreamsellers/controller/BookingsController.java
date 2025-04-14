package org.dreamsellers.controller;

import io.swagger.models.Response;
import lombok.AllArgsConstructor;
import org.api.BookingsApi;
import org.dreamsellers.service.BookingsService;
import org.model.BookingResponseDto;
import org.model.CreateBookingDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class BookingsController implements BookingsApi {
    private BookingsService bookingsService;

    @Override
    public ResponseEntity<List<BookingResponseDto>> bookingsUserIdGet(Long userId) {
        List<BookingResponseDto> bookings = bookingsService.getAllBookingsByUser(userId);

        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookingResponseDto> bookingsBookingBookingIdGet(Long bookingId) {
        BookingResponseDto booking = bookingsService.getBookingByBookingId(bookingId);

        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookingResponseDto> bookingsCreatePost(CreateBookingDto createBookingDto) {
        BookingResponseDto booking = bookingsService.createBooking(createBookingDto);

        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookingResponseDto> bookingsBookingIdCancelPost(Long bookingId) {
        BookingResponseDto booking = bookingsService.cancelBooking(bookingId);

        return new ResponseEntity<>(booking, HttpStatus.OK);
    }
}
