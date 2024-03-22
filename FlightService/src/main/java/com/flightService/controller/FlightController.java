package com.flightService.controller;


import com.flightService.Dtos.FlightDto;
import com.flightService.Dtos.FlightDto1;
import com.flightService.repository.FlightRepository;
import com.flightService.service.FlightService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<FlightDto1> addOneFlight(@RequestBody FlightDto flightDto,@RequestHeader("loggedInUser") String userName){

            FlightDto1 flightDto1 = flightService.createOneFlight(flightDto);
            ResponseEntity<FlightDto1> flightDtoResponseEntity = new ResponseEntity<>(flightDto1, HttpStatus.CREATED);
            return flightDtoResponseEntity;
    }

    @GetMapping
    public List<FlightDto1> getAllFlight(@RequestHeader("loggedInUser") String userName){
       List<FlightDto1> listOfFlights = flightService.getAllFlight();
       return listOfFlights;
    }
    @GetMapping("/destination/{destination}")
    public List<FlightDto1> getFlightBy_Destination(@PathVariable("destination") String destination,@RequestHeader("loggedInUser") String userName){
        List<FlightDto1> flightDto=flightService.getFlightByDestination(destination);
        return flightDto;

    }

    @GetMapping("/origin/{origin}")
    public List<FlightDto1> getFlightByOrigin(@PathVariable("origin") String origin,@RequestHeader("loggedInUser") String userName){
        List<FlightDto1> flightDto=flightService.getFlightBySource(origin);
        return flightDto;

    }
    @GetMapping("/date/{date}")
    public List<FlightDto1> getFlightBy_Date(@PathVariable("date") String date,@RequestHeader("loggedInUser") String userName){
        List<FlightDto1> flightDto=flightService.getFlightByDate(date);
        return flightDto;

    }


    @GetMapping("/{flightId}")
    public ResponseEntity<FlightDto1> getOneFlight(@PathVariable("flightId") long flightId){
        FlightDto1 oneFlight = flightService.getOneFlight(flightId);
        return new ResponseEntity<>(oneFlight,HttpStatus.OK);
    }
    @GetMapping("/user/{userId}")
    public List<FlightDto> getAllFlight(@PathVariable("userId") long userId){
        List<FlightDto> listOfFlights = flightService.getAllFlight(userId);
        return listOfFlights;
    }

    @DeleteMapping("/deleteflight/{flightID}")
    public String delFlightByFlightNumber(@PathVariable("flightID") long flightID){
        flightService.deleteOneFlight(flightID);
        return "flight with flightNumber  "+flightID+"  deleted successfully";
    }

}
