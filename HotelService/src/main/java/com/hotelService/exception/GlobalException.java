package com.hotelService.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.NOT_FOUND)
public class GlobalException {


    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String > noRecordFound(CustomException customException){
        ResponseEntity<String> stringResponseEntity = new ResponseEntity<>(customException.getMessage(), HttpStatus.NOT_FOUND);
        return stringResponseEntity;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String > globalException(Exception e){

        ResponseEntity<String> notFound = new ResponseEntity<>("Not found ", HttpStatus.BAD_REQUEST);
        return notFound;

    }

}
