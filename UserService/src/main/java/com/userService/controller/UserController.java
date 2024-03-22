package com.userService.controller;

import com.userService.dto.UserDto;
import com.userService.otherServices.Payment;
import com.userService.repository.UserRepository;
import com.userService.service.UserService;
import com.userService.service.feignclient.PaymentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentClient paymentClient;


    @PostMapping("/createUser")
    public ResponseEntity<UserDto> postOneUser(@RequestBody UserDto userDto){
        UserDto userDto1=userService.createOneUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }


    @GetMapping("/allusers")
    public List<UserDto> getAll_User(){
        List<UserDto> userDtoList=userService.getAllUser();
        return userDtoList;
    }
    @GetMapping("/{userId}")
    public UserDto getOne_User(@PathVariable("userId") long userId){
        UserDto userDtoList=userService.getOne_User(userId);
        return userDtoList;
    }

    @GetMapping("/email/{email}")
    public UserDto getUserByEmail(@PathVariable("email") String email){
        UserDto userDto=userService.findbyEmail(email);
        return userDto;
    }

    @DeleteMapping("/deleteuser/{userId}")
    public String deleteUserById(@PathVariable("userId") long userId){
        userService.deleteUser_byId(userId);
        return "user with id "+userId+" is deleted";
    }

    @PostMapping("/payment/hotel/{hotelId}/{customerId}")
    public ResponseEntity<Payment> CreateOnePaymentByHotelIdCustomerId(@RequestBody Payment payment, @PathVariable("hotelId") long hotelId,
                                                                       @PathVariable("customerId") long customerId
                                                                      )
    {
        Payment payment1 = paymentClient.CreateOnePaymentByHotelId_CustomerId(payment, hotelId, customerId);
        return new ResponseEntity<>(payment1,HttpStatus.CREATED);
    }

    @PostMapping("/payment/flight/{flightId}/{customerId}")
    public ResponseEntity<Payment> CreateOnePaymentByFlightId_CustomerId(@RequestBody Payment payment, @PathVariable("flightId") long flightId, @PathVariable("customerId") long customerId)
    {
        Payment payment1 = paymentClient.CreateOnePaymentByFlightId_CustomerId(payment, flightId, customerId);
        return new ResponseEntity<>(payment1,HttpStatus.CREATED);
    }

    @PostMapping("/flight_hotel/{flightId}/{hotelId}/{customerId}")
    public ResponseEntity<Payment> CreateOnePaymentByFlightIdHotelIdCustomerId(@RequestBody Payment payment, @PathVariable("flightId") long flightId,@PathVariable("hotelId") long hotelId,@PathVariable("customerId") long customerId)
    {
        Payment payment1 = paymentClient.CreateOnePaymentByFlightId_HotelId_CustomerId(payment, flightId, hotelId, customerId);
        return new ResponseEntity<>(payment1,HttpStatus.CREATED);
    }
}
