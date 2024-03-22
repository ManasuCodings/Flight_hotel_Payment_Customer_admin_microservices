package com.payment.controller;


import com.payment.dtos.PaymentDto;
import com.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/hotel/{hotelId}/{customerId}")
    public ResponseEntity<PaymentDto> CreateOnePaymentByHotelId_CustomerId(@RequestBody PaymentDto paymentDto,
                                                                           @PathVariable("hotelId") long hotelId,
                                                                           @PathVariable("customerId") long customerId)
    {


        PaymentDto paymentDto1 = paymentService.createOnePayment(paymentDto, hotelId, customerId);
        ResponseEntity<PaymentDto> paymentDtoResponseEntity = new ResponseEntity<>(paymentDto1, HttpStatus.CREATED);
        return paymentDtoResponseEntity;
    }

    @PostMapping("/flight/{flightId}/{customerId}")
    public ResponseEntity<PaymentDto> CreateOnePaymentByFlightId_CustomerId(@RequestBody PaymentDto paymentDto, @PathVariable("flightId") long flightId,
                                                                             @PathVariable("customerId") long customerId) {

        PaymentDto paymentDto1 = paymentService.createOnePaymentForFlight(paymentDto, flightId, customerId);
        ResponseEntity<PaymentDto> paymentDtoResponseEntity = new ResponseEntity<>(paymentDto1, HttpStatus.CREATED);
        return paymentDtoResponseEntity;
    }
    @PostMapping("/flight_hotel/{flightId}/{hotelId}/{customerId}")
    public ResponseEntity<PaymentDto> CreateOnePaymentByFlightId_HotelId_CustomerId(@RequestBody PaymentDto paymentDto, @PathVariable("flightId") long flightId
                                                                            ,@PathVariable("hotelId") long hotelId,@PathVariable("customerId") long customerId) {

        PaymentDto paymentDto1 = paymentService.createOnePaymentForFlight_hotel(paymentDto, flightId,hotelId, customerId);
        ResponseEntity<PaymentDto> paymentDtoResponseEntity = new ResponseEntity<>(paymentDto1, HttpStatus.CREATED);
        return paymentDtoResponseEntity;
    }
    @GetMapping("/user/{userId}")
    public List<PaymentDto> getAllPaymentOfOneUser(@PathVariable("userId") long userId){
        List<PaymentDto> paymentDtoList=paymentService.getAllPayment(userId);
        return paymentDtoList;
    }
    @GetMapping("/allpayments")
    public List<PaymentDto> getAllPayment(){
        List<PaymentDto> paymentDtoList=paymentService.getAllPayment();
        return paymentDtoList;
    }
}
