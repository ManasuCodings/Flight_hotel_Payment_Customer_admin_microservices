package com.userService.service;

import com.userService.dto.UserDto;
import com.userService.entity.User;
import com.userService.exception.CustomException;
import com.userService.repository.UserRepository;
import com.userService.service.feignclient.FlightClient;
import com.userService.service.feignclient.HotelClient;
import com.userService.service.feignclient.PaymentClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HotelClient hotelClient;
    @Autowired
    private FlightClient flightClient;
    @Autowired
    private PaymentClient paymentClient;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto createOneUser(UserDto userDto) {

        User map = modelMapper.map(userDto, User.class);
        map.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User save = userRepository.save(map);
        UserDto map1 = modelMapper.map(save, UserDto.class);
        return map1;
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> all = userRepository.findAll();
        List<User> collect1 = all.stream().map(x -> {
            x.setPaymentList(paymentClient.getPaymentByUserId(x.getId()));
            x.setHotelList(hotelClient.getHotelByUserId(x.getId()));
            x.setFlightList(flightClient.getFlightByUserId(x.getId()));
            return x;
        }).collect(Collectors.toList());

        List<UserDto> collect = collect1.stream().map(x -> modelMapper.map(x, UserDto.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public UserDto getOne_User(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException("no such User with this id "+userId));
        user.setHotelList(hotelClient.getHotelByUserId(userId));
        user.setFlightList(flightClient.getFlightByUserId(userId));
        user.setPaymentList(paymentClient.getPaymentByUserId(userId));
        UserDto map = modelMapper.map(user, UserDto.class);
        return map;
    }

    @Override
    public void deleteUser_byId(long userId) {
        userRepository.findById(userId).orElseThrow(()->new CustomException("no user with id "+userId));
        userRepository.deleteById(userId);
    }

    @Override
    public UserDto findbyEmail(String email) {
        User user = userRepository.findUserByMail(email).orElseThrow(() -> new CustomException("no user with email " + email));
        UserDto map = modelMapper.map(user, UserDto.class);
        return map;
    }
}
