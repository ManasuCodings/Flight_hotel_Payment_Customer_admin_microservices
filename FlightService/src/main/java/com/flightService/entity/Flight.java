package com.flightService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String flightNumber;
    private String ticketId;
    @Column(nullable = false)
    private String sourceAirport;
    @Column(nullable = false)
    private String destinationAirport;
    private String departureDate;
    private String departureTime;
    private String arrivalDate;
    private String arrivalTime;
    private long customerId;


    // Constructors, getters, and setters
}

