package com.payment.service;

import com.payment.dtos.PaymentDto;
import com.payment.entity.Payment;
import com.payment.repository.PaymentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PaymentDto createOnePayment(PaymentDto paymentDto, long hotelId, long customerId) {
        Payment map = modelMapper.map(paymentDto, Payment.class);
        map.setCustomerId(customerId);
        map.setHotelId(hotelId);
        Payment save = paymentRepo.save(map);
        PaymentDto map1 = modelMapper.map(save, PaymentDto.class);
        return map1;
    }

    @Override
    public PaymentDto createOnePaymentForFlight(PaymentDto paymentDto, long flightId, long customerId) {
        Payment map = modelMapper.map(paymentDto, Payment.class);
        map.setCustomerId(customerId);
        map.setFlightId(flightId);
        Payment save = paymentRepo.save(map);
        PaymentDto map1 = modelMapper.map(save, PaymentDto.class);
        return map1;

    }

    @Override
    public PaymentDto createOnePaymentForFlight_hotel(PaymentDto paymentDto, long flightId, long hotelId, long customerId) {
        Payment map = modelMapper.map(paymentDto, Payment.class);
        map.setCustomerId(customerId);
        map.setFlightId(flightId);
        map.setHotelId(hotelId);
        Payment save = paymentRepo.save(map);
        PaymentDto map1 = modelMapper.map(save, PaymentDto.class);
        return map1;
    }

    @Override
    public List<PaymentDto> getAllPayment(long userId) {
        List<Payment> paymentList=paymentRepo.findAllPaymentByCustomerId(userId);
        List<PaymentDto> collect = paymentList.stream().map(x -> modelMapper.map(x, PaymentDto.class)).collect(Collectors.toList());
        for (PaymentDto pd:collect
             ) {
            System.out.println(pd.toString());
            System.out.println("_______________________");
        }
        return collect;
    }

    @Override
    public List<PaymentDto> getAllPayment() {

        List<Payment> all = paymentRepo.findAll();
        List<PaymentDto> collect = all.stream().map(x -> modelMapper.map(x, PaymentDto.class)).collect(Collectors.toList());
        return collect;
    }
}
