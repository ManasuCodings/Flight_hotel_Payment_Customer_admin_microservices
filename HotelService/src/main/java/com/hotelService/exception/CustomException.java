package com.hotelService.exception;

public class CustomException extends RuntimeException {
    public CustomException(String str){
        System.out.println(str);
    }
}
