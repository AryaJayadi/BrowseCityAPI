package com.arya.browsecityapi.app.service;

import com.arya.browsecityapi.app.CityScoreWrapper;
import com.arya.browsecityapi.app.exception.CityParameterException;

import java.math.BigDecimal;
import java.util.List;

public interface ICityService {
    List<CityScoreWrapper> getCitySuggestions(String q, BigDecimal latitude, BigDecimal longitude) throws CityParameterException;
}
