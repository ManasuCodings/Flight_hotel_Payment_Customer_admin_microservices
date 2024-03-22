package com.payment.repository;

import com.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentRepo extends JpaRepository<Payment,Long> {

    @Query(value = "select * from payment where customer_id= :customerId",nativeQuery = true)
    List<Payment> findAllPaymentByCustomerId(@Param("customerId") long customerId);
}
