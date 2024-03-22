package com.hotelService.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class HotelDto {
    private Long id;
    private String hotelName;
    private String location;
    private long chargePerDay;
    private String checkInDate;
    private String checkOutDate;
    private long customerId;
}
