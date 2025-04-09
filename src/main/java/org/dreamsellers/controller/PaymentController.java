package org.dreamsellers.controller;

import io.swagger.models.Response;
import lombok.AllArgsConstructor;
import org.api.PaymentsApi;
import org.dreamsellers.service.PaymentService;
import org.model.PaymentDto;
import org.model.PaymentResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class PaymentController implements PaymentsApi {
    private final PaymentService paymentService;

    @Override
    public ResponseEntity<PaymentResponseDto> paymentsPost(PaymentDto paymentDto) {
        PaymentResponseDto paymentResponseDto = paymentService.makePayment(paymentDto);

        return new ResponseEntity<>(paymentResponseDto, HttpStatus.OK);
    }
}
