package org.dreamsellers.service;

import lombok.AllArgsConstructor;
import org.dreamsellers.errors.UserNotFoundException;
import org.dreamsellers.model.BookingEntity;
import org.dreamsellers.model.UserEntity;
import org.dreamsellers.repository.BookingsRepository;
import org.dreamsellers.repository.ListingRepository;
import org.dreamsellers.repository.UserRepository;
import org.model.BookingResponseDto;
import org.model.CreateBookingDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingsService {
    private final BookingsRepository bookingsRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ListingRepository listingRepository;

    public List<BookingResponseDto> getAllBookingsByUser(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> UserNotFoundException.create("User with id " + userId + " not found"));

        return bookingsRepository.getAllByUserId(userId)
                .stream()
                .map(bookingEntity -> modelMapper.map(bookingEntity, BookingResponseDto.class))
                .collect(Collectors.toList());
    }

    public BookingResponseDto getBookingByBookingId(Long bookingId) {
        BookingEntity booking = bookingsRepository
                .findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking with id " + bookingId + " not found"));

        return modelMapper.map(booking, BookingResponseDto.class);
    }

    public BookingResponseDto createBooking(CreateBookingDto createBookingDto) {
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setCreatedAt(LocalDateTime.now());
        bookingEntity.setUpdatedAt(LocalDateTime.now());
        bookingEntity.setStatus(BookingResponseDto.StatusEnum.PENDING);
        bookingEntity.setPaymentStatus(BookingResponseDto.PaymentStatusEnum.PENDING);
        bookingEntity.setUser(userRepository.getUserById(createBookingDto.getUid()));
        bookingEntity.setListing(listingRepository
                .getListingById(createBookingDto.getListingId())
                .orElseThrow(() -> new RuntimeException("No such listing"))
        );
        bookingEntity.setTotalPrice(bookingEntity.getListing().getPrice());

        BookingEntity savedBooking = bookingsRepository.save(bookingEntity);
        return modelMapper.map(savedBooking, BookingResponseDto.class);
    }

    public BookingResponseDto cancelBooking(Long bookingId) {
        BookingEntity bookingEntity = bookingsRepository
                .findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking with id " + bookingId + " not found"));

        bookingEntity.setStatus(BookingResponseDto.StatusEnum.CANCELLED);
        BookingEntity savedBooking = bookingsRepository.save(bookingEntity);

        return modelMapper.map(savedBooking, BookingResponseDto.class);
    }

}
