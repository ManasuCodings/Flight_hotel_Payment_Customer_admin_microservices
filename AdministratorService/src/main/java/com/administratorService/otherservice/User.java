package com.administratorService.otherservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {

    private Long id;
    private String username;
    private String email;
    private List<Payment> paymentList;
    private List<Hotel> hotelList;
    private List<Flight> flightList;

}
