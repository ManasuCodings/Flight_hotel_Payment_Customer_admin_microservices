package com.flightService.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FlightDto1 {
    private Long id;
    private String flightNumber;
    private String ticketId;
    private String sourceAirport;
    private String destinationAirport;
    private String departureDate;
    private String departureTime;
    private String arrivalDate;
    private String arrivalTime;
}
