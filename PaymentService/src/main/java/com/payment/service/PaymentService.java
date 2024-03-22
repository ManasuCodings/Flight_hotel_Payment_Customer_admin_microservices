package com.payment.service;

import com.payment.dtos.PaymentDto;

import java.util.List;

public interface PaymentService {
    PaymentDto createOnePayment(PaymentDto paymentDto, long hotelId, long customerId);

    PaymentDto createOnePaymentForFlight(PaymentDto paymentDto, long flightId, long customerId);

    PaymentDto createOnePaymentForFlight_hotel(PaymentDto paymentDto, long flightId, long hotelId, long customerId);

    List<PaymentDto> getAllPayment(long userId);

    List<PaymentDto> getAllPayment();
}
