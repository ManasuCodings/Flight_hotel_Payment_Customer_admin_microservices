package com.payment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Payment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long amount;

    @Column(nullable = false)
    private String paymentDate;

    @Column(nullable = false)
    private String paymentFor;

    private long customerId;

    private long hotelId;

    private long flightId;
}
