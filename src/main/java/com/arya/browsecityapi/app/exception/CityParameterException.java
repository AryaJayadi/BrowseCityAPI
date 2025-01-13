package com.arya.browsecityapi.app.exception;

public class CityParameterException extends Exception {

    public CityParameterException(String message) {
        super(String.format("City Parameter Exception: %s", message));
    }
}
