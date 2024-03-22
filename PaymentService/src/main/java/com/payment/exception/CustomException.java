package com.payment.exception;

public class CustomException extends RuntimeException{

    public CustomException(String str){
        System.out.println(str);
    }

}
