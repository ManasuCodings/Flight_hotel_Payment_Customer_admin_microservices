package com.userService.service.feignclient;

import com.userService.otherServices.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelClient {
    @GetMapping("/hotel/user/{userId}")
    List<Hotel> getHotelByUserId(@PathVariable("userId") long userId);
}
