package com.hotelService.service;

import com.hotelService.dtos.HotelDto;
import com.hotelService.entity.Hotel;
import com.hotelService.exception.CustomException;
import com.hotelService.repository.HotelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<HotelDto> getHotelsByLocation(String hotelLocation) {
        List<Hotel> hotel=hotelRepository.findHotelByLocation(hotelLocation);
        List<HotelDto> collect = hotel.stream().map(x -> modelMapper.map(x, HotelDto.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public HotelDto createOneHotel(HotelDto hotelDto) {
        Hotel map = modelMapper.map(hotelDto, Hotel.class);
        Hotel save = hotelRepository.save(map);
        HotelDto map1 = modelMapper.map(save, HotelDto.class);
        return map1;
    }

    @Override
    public List<HotelDto> getAllHotel() {
        List<Hotel> all = hotelRepository.findAll();
        List<HotelDto> collect = all.stream().map(x -> modelMapper.map(x, HotelDto.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<HotelDto> getHotelsByDate(String date) {
        List<Hotel> hotel=hotelRepository.findHotelByDate(date);
        List<HotelDto> collect = hotel.stream().map(x -> modelMapper.map(x, HotelDto.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public HotelDto getHotelById(long hotelId) {

        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new CustomException("hotel with this id not present"));
        HotelDto map = modelMapper.map(hotel, HotelDto.class);
        return map;
    }

    @Override
    public void deleteOneHotel(long hotelId) {
        hotelRepository.findById(hotelId).orElseThrow(() -> new CustomException("no hotel with id " + hotelId));
        hotelRepository.deleteById(hotelId);
    }

    @Override
    public List<HotelDto> getHotelByUserId(long userId) {
       List<Hotel> hotel= hotelRepository.getHotelByUserId(userId);
        List<HotelDto> collect = hotel.stream().map(x -> modelMapper.map(x, HotelDto.class)).collect(Collectors.toList());
        return collect;
    }
}
