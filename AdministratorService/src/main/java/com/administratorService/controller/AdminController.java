package com.administratorService.controller;

import com.administratorService.dto.AdministratorDto;
import com.administratorService.exception.CustomException;
import com.administratorService.feignservices.FlightClient;
import com.administratorService.feignservices.HotelClient;
import com.administratorService.feignservices.UserClient;
import com.administratorService.otherservice.Flight;
import com.administratorService.otherservice.Hotel;
import com.administratorService.otherservice.User;
import com.administratorService.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private FlightClient flightClient;
    @Autowired
    private HotelClient hotelClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    private AdminService adminService;
    @PostMapping("/addAdmin")
    public ResponseEntity<AdministratorDto> addOneAdmin(@RequestBody AdministratorDto administratorDto ){

        AdministratorDto administratorDto1=adminService.createOneAdmin(administratorDto);
        return new ResponseEntity<>(administratorDto1,HttpStatus.CREATED);
    }

    @GetMapping("/email/{email}")
    public AdministratorDto getAdminByEmail(@PathVariable("email") String email){
        AdministratorDto administratorDto=adminService.getAdminBy_Email(email);
        return administratorDto;
    }
    @PostMapping("/addflight")
    public ResponseEntity<Flight> addOneFlight(@RequestBody Flight flight){
        Flight flight1=flightClient.addOneFlight(flight);
        ResponseEntity<Flight> flightResponseEntity = new ResponseEntity<>(flight1, HttpStatus.CREATED);
        return flightResponseEntity;
    }

    @DeleteMapping("/deleteflight/{flightId}")
    public String deleteFlightByFlightId(@PathVariable("flightId") long flightId){
        flightClient.deleteOneFlight(flightId).orElseThrow(() -> new CustomException("no flight with id " + flightId));
        return "flight with id "+flightId+" is deleted";
    }
    @PostMapping("/addUser")
    public ResponseEntity<User> addOneUser(@RequestBody User user){
        User user1 = userClient.addOneUser(user);
        ResponseEntity<User> userResponseEntity = new ResponseEntity<>(user1, HttpStatus.CREATED);

        return userResponseEntity;
    }

    @DeleteMapping("/deleteUser/{userId}")
    public String deleteUseByUserId(@PathVariable("userId") long userId){
        userClient.deleteOneUser(userId).orElseThrow(()-> new CustomException("no such user with Id"+ userId));

        return "user with id "+userId+" is deleted";
    }
    @PostMapping("/addHotel")
    public ResponseEntity<Hotel> addOneHotel(@RequestBody Hotel hotel){

        Hotel hotel1 = hotelClient.addOneHotel(hotel);
        ResponseEntity<Hotel> hotelResponseEntity = new ResponseEntity<>(hotel1, HttpStatus.CREATED);

        return hotelResponseEntity;
    }

    @DeleteMapping("/deletehotel/{hotelId}")
    public String deleteHotelByHotelId(@PathVariable("hotelId") long hotelId){
        hotelClient.deleteOneHotel(hotelId).orElseThrow(()->new CustomException("no hotel with id "+hotelId));
        return "hotel with id "+hotelId+" is deleted";
    }
}
