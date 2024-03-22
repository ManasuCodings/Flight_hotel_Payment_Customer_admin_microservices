package com.hotelService.repository;

import com.hotelService.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel,Long>
{

    @Query(value = "select * from hotel where location= :location",nativeQuery = true)
    List<Hotel> findHotelByLocation(@Param("location") String hotelLocation);
    @Query(value = "select * from hotel where check_in_date = :date",nativeQuery = true)
    List<Hotel> findHotelByDate(@Param("date") String date);

    @Query(value = "select * from hotel where customer_id= :userId",nativeQuery = true)
    List<Hotel> getHotelByUserId(@Param("userId") long userId);


}
