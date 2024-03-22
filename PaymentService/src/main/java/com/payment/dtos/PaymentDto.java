package com.payment.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PaymentDto {
    private long id;
    private long amount;
    private String paymentDate;
    private String paymentFor;
    private long customerId;
    private long hotelId;
    private long flightId;
}
