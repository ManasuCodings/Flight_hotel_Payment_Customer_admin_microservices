package com.flightService.repository;

import com.flightService.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    Optional<Flight> findByFlightNumber(String flightNumber);

    @Query(value = "select * from flight where customer_id= :customerId",nativeQuery = true)
    List<Flight> findFlightByCustomerId(@Param("customerId") long customerId);

    @Query(value = "select * from flight where source_airport= :origin",nativeQuery = true)
    List<Flight> getFlightByOriginAirport(@Param("origin") String origin);

    @Query(value = "select * from flight where departure_date= :date",nativeQuery = true)
    List<Flight> getFlightByDateWise(@Param("date") String date);

    @Query(value = "select * from flight where destination_airport= :destination",nativeQuery = true)
    List<Flight> getFlightByDestinationWise(@Param("destination") String destination);


}
