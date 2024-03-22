package com.administratorService.feignservices;

import com.administratorService.otherservice.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelClient {
    @PostMapping("/hotel")
    Hotel addOneHotel(@RequestBody Hotel hotel);

    @DeleteMapping("/hotel/deleteHotel/{HotelId}")
    Optional<String> deleteOneHotel(@PathVariable("HotelId") long HotelId);
}