package com.userService.service.feignclient;

import com.userService.otherServices.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "PAYMENT-SERVICE")
public interface PaymentClient {


    @GetMapping("/payment/user/{userId}")
    List<Payment> getPaymentByUserId(@PathVariable("userId") long userId);

    @PostMapping("/payment/hotel/{hotelId}/{customerId}")
    Payment CreateOnePaymentByHotelId_CustomerId(@RequestBody Payment payment, @PathVariable("hotelId") long hotelId, @PathVariable("customerId") long customerId);

    @PostMapping("/payment/flight/{flightId}/{customerId}")
    Payment CreateOnePaymentByFlightId_CustomerId(@RequestBody Payment payment, @PathVariable("flightId") long flightId, @PathVariable("customerId") long customerId);

    @PostMapping("/payment/flight_hotel/{flightId}/{hotelId}/{customerId}")
    Payment CreateOnePaymentByFlightId_HotelId_CustomerId(@RequestBody Payment payment, @PathVariable("flightId") long flightId,@PathVariable("hotelId") long hotelId,@PathVariable("customerId") long customerId);

}
