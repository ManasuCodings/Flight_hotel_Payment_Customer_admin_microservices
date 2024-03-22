package com.userService.dto;

import com.userService.otherServices.Flight;
import com.userService.otherServices.Hotel;
import com.userService.otherServices.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;
    private List<Payment> paymentList;
    private List<Hotel> hotelList;
    private List<Flight> flightList;

}
