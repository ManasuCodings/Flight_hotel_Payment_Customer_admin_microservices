package com.userService.service.feignclient;

import com.userService.otherServices.Flight;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "FLIGHT-SERVICE")
public interface FlightClient {

    @GetMapping("/flight/user/{userId}")
    List<Flight> getFlightByUserId(@PathVariable("userId") long userId);
}
