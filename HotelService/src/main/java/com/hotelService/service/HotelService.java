package com.hotelService.service;

import com.hotelService.dtos.HotelDto;

import java.util.List;

public interface HotelService {

    List<HotelDto> getHotelsByLocation(String hotelLocation);
    HotelDto createOneHotel(HotelDto hotelDto);

    List<HotelDto> getAllHotel();

    List<HotelDto> getHotelsByDate(String date);

    HotelDto getHotelById(long hotelId);

    void deleteOneHotel(long hotelId);

    List<HotelDto> getHotelByUserId(long userId);
}
