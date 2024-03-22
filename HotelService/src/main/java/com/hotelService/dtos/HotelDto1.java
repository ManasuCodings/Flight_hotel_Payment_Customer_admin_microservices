package com.hotelService.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class HotelDto1 {
    private Long id;
    private String hotelName;
    private String location;
    private String checkInDate;
    private String checkOutDate;
    private int number_of_days;
    private int total_amount;
    private long customer_id;
}
