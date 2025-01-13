package com.arya.browsecityapi.api.exception;

import com.arya.browsecityapi.app.exception.CityParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@ControllerAdvice
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
