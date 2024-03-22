package com.administratorService.otherservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Hotel {
    private Long id;
    private String hotelName;
    private String location;
    private long chargePerDay;
    private String checkInDate;
    private String checkOutDate;
    private long customerId;
}
