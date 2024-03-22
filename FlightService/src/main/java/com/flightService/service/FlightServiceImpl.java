package com.flightService.service;

import com.flightService.Dtos.FlightDto;
import com.flightService.Dtos.FlightDto1;
import com.flightService.entity.Flight;
import com.flightService.exception.CustomException;
import com.flightService.repository.FlightRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public FlightDto1 createOneFlight(FlightDto flightDto) {


        Flight flight = modelMapper.map(flightDto, Flight.class);
        Flight save = flightRepository.save(flight);
        FlightDto1 map1 = modelMapper.map(save, FlightDto1.class);
        return map1;
    }

    @Override
    public List<FlightDto1> getAllFlight() {
        List<Flight> all = flightRepository.findAll();
        List<FlightDto1> collect = all.stream().map(x -> modelMapper.map(x, FlightDto1.class)).collect(Collectors.toList());
        return collect;
    }
    @Override
    public FlightDto1 getOneFlight(long flightId){
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new CustomException("no such flight with id "+flightId));
        FlightDto1 map = modelMapper.map(flight, FlightDto1.class);
        return map;
    }

    @Override
    public List<FlightDto> getAllFlight(long userId) {
        List<Flight> all = flightRepository.findFlightByCustomerId(userId);
        List<FlightDto> collect = all.stream().map(x -> modelMapper.map(x, FlightDto.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public void deleteOneFlight(long flightID) {
        flightRepository.findById(flightID).orElseThrow(()->new CustomException("no flight with id "+flightID));
        flightRepository.deleteById(flightID);
    }

    @Override
    public List<FlightDto1> getFlightBySource(String origin) {

        List<Flight> flightByOriginAirport = flightRepository.getFlightByOriginAirport(origin);
        List<FlightDto1> collect = flightByOriginAirport.stream().map(x -> modelMapper.map(x, FlightDto1.class)).collect(Collectors.toList());
        return collect;

    }

    @Override
    public List<FlightDto1> getFlightByDate(String date) {
        List<Flight> flightByDate = flightRepository.getFlightByDateWise(date);
        List<FlightDto1> collect = flightByDate.stream().map(x -> modelMapper.map(x, FlightDto1.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<FlightDto1> getFlightByDestination(String destination) {
        List<Flight> flightByDestinationWise = flightRepository.getFlightByDestinationWise(destination);
        List<FlightDto1> collect = flightByDestinationWise.stream().map(x -> modelMapper.map(x, FlightDto1.class)).collect(Collectors.toList());
        return collect;
    }
}
