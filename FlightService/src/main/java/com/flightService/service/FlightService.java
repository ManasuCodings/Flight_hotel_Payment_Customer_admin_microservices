package com.flightService.service;

import com.flightService.Dtos.FlightDto;
import com.flightService.Dtos.FlightDto1;

import java.util.List;

public interface FlightService {
    FlightDto1 createOneFlight(FlightDto flightDto);

    List<FlightDto1> getAllFlight();

    FlightDto1 getOneFlight(long flightId);

    List<FlightDto> getAllFlight(long userId);

    void deleteOneFlight(long flightID);

    List<FlightDto1> getFlightBySource(String origin);

    List<FlightDto1> getFlightByDate(String date);

    List<FlightDto1> getFlightByDestination(String destination);
}
