package com.userService.entity;

import com.userService.otherServices.Flight;
import com.userService.otherServices.Hotel;
import com.userService.otherServices.Payment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false,unique = true)
    private String email;

    private String password;

    @Column(nullable = false)
    private String role;

    transient List<Payment> paymentList;

    transient List<Hotel> hotelList;

    transient List<Flight> flightList;
}
