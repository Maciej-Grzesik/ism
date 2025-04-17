package org.dreamsellers.service;

import lombok.AllArgsConstructor;
import org.dreamsellers.model.BookingEntity;
import org.dreamsellers.repository.BookingsRepository;
import org.model.BookingResponseDto;
import org.model.PaymentDto;
import org.model.PaymentResponseDto;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentService {
    private final BookingsRepository bookingsRepository;

    public PaymentResponseDto makePayment(PaymentDto paymentDto) {
        BookingEntity bookingEntity = bookingsRepository
                .findBookingEntityById(paymentDto.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        bookingEntity.setPaymentStatus(BookingResponseDto.PaymentStatusEnum.PAID);
        bookingsRepository.save(bookingEntity);

        PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
        paymentResponseDto.setBookingId(paymentDto.getBookingId());

        return paymentResponseDto;
    }
}
