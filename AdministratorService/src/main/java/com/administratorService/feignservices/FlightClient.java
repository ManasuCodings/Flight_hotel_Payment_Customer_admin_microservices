package com.administratorService.feignservices;

import com.administratorService.otherservice.Flight;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(name = "FLIGHT-SERVICE")
public interface FlightClient {

    @PostMapping("/flight")
    Flight addOneFlight(@RequestBody Flight flight);

    @DeleteMapping("/flight/deleteflight/{flightID}")
    Optional<String> deleteOneFlight(@PathVariable("flightID") long flightID );
}
