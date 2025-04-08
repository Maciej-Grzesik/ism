package org.dreamsellers.service;

import lombok.AllArgsConstructor;
import org.dreamsellers.model.BookingEntity;
import org.dreamsellers.repository.BookingsRepository;
import org.model.BookingResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingsService {
    private final BookingsRepository bookingsRepository;
    private final ModelMapper modelMapper;

    public List<BookingResponseDto> getAllBookingsByUser(long userId) {
        return bookingsRepository.getAllByUserId(userId)
                .stream()
                .map(bookingEntity -> modelMapper.map(bookingEntity, BookingResponseDto.class))
                .collect(Collectors.toList());
    }

}
