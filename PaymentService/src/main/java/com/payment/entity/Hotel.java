package com.payment.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Hotel {


    private Long id;
    private String hotelName;

    private String location;

    private long chargePerDay;

    private Date checkInDate;

    private Date checkOutDate;

    private long customerId;
}
