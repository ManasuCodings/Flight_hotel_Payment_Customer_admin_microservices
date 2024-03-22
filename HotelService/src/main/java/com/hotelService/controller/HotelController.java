package com.hotelService.controller;

import com.hotelService.dtos.HotelDto;
import com.hotelService.dtos.HotelDto1;
import com.hotelService.service.HotelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private ModelMapper modelMapper;
    @PostMapping
    public ResponseEntity<HotelDto1> addOneHotel(@RequestBody HotelDto hotelDto){
        ResponseEntity<HotelDto1> hotelDtoResponseEntity;
        if(hotelDto.getCustomerId()!=0 && hotelDto.getCheckInDate()!=null){

            String checkInDate = hotelDto.getCheckInDate();
            String[] checkInDate1 = checkInDate.split("-");
            String checkOutDate = hotelDto.getCheckOutDate();
            String[] checkOutDate1 = checkOutDate.split("-");

            int day_CI=Integer.parseInt(checkInDate1[0]);
            int month_CI=Integer.parseInt(checkInDate1[1]);
            int year_CI=Integer.parseInt(checkInDate1[2]);

            int day_CO=Integer.parseInt(checkOutDate1[0]);
            int month_CO=Integer.parseInt(checkOutDate1[1]);
            int year_CO=Integer.parseInt(checkOutDate1[2]);

            LocalDate date1 = LocalDate.of(year_CI,month_CI,day_CI);
            LocalDate date2 = LocalDate.of(year_CO, month_CO, day_CO);

            int days = date2.getDayOfYear() - date1.getDayOfYear();
            int total_amount = days * (int)hotelDto.getChargePerDay();

            HotelDto hotelDto1=hotelService.createOneHotel(hotelDto);
            HotelDto1 map = modelMapper.map(hotelDto1, HotelDto1.class);
            map.setNumber_of_days(days);
            map.setTotal_amount(total_amount);
            map.setCustomer_id(hotelDto1.getCustomerId());
            hotelDtoResponseEntity = new ResponseEntity<>(map, HttpStatus.CREATED);
        }else if (hotelDto.getHotelName()!=null){

            HotelDto hotelDto1=hotelService.createOneHotel(hotelDto);
            HotelDto1 map = modelMapper.map(hotelDto1, HotelDto1.class);
            hotelDtoResponseEntity = new ResponseEntity<>(map, HttpStatus.CREATED);

        }else {
            hotelDtoResponseEntity=null;
        }

        return hotelDtoResponseEntity;
    }
    @GetMapping("/allhotels")
    public List<HotelDto> getAllHotels(@RequestHeader("loggedInUser") String userName){
        System.out.println("logged in username : "+userName);
        List<HotelDto> listOfHotels = hotelService.getAllHotel();
        return listOfHotels;
    }
    @GetMapping("/hotelLocation/{hotelLocation}")
    public List<HotelDto> getHotelByLocation(@PathVariable("hotelLocation") String hotelLocation){

        List<HotelDto> listOfHotels = hotelService.getHotelsByLocation(hotelLocation);
        return listOfHotels;
    }
    @GetMapping("/date/{date}")
    public List<HotelDto> getHotelByDate(@PathVariable("date") String date,@RequestHeader("loggedInUser") String userName){
        List<HotelDto> listOfHotels = hotelService.getHotelsByDate(date);
        return listOfHotels;
    }
    @GetMapping("/hotelId/{HotelId}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable("HotelId") long HotelId){

        HotelDto hoteldto = hotelService.getHotelById(HotelId);
        ResponseEntity<HotelDto> hotelResponseEntity = new ResponseEntity<>(hoteldto, HttpStatus.OK);
        return hotelResponseEntity;
    }

    @GetMapping("/user/{userId}")
    public List<HotelDto> getHotelByUserId(@PathVariable("userId") long userId){
        List<HotelDto> hoteldto = hotelService.getHotelByUserId(userId);
        return hoteldto;
    }


    @DeleteMapping("/deleteHotel/{HotelId}")
    public String delHotelByHotelId(@PathVariable("HotelId") long HotelId,@RequestHeader("loggedInUser") String userName){
        hotelService.deleteOneHotel(HotelId);
        return "Hotel with HotelId  "+HotelId+"  deleted successfully";
    }
}
