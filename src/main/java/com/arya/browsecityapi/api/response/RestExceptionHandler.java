package com.arya.browsecityapi.api.response;

import com.arya.browsecityapi.app.exception.CityParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = CityParameterException.class)
    public ResponseEntity<ApiError> handleParameterException() {
        ApiError error = ApiError.builder()
                .errorCode(400)
                .errorMessage("Required Parameter Not Found!")
                .timestamp(new Date())
                .build();
        return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
    }
}
