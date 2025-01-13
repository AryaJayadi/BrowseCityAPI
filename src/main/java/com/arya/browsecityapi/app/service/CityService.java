package com.arya.browsecityapi.app.service;

import com.arya.browsecityapi.app.City;
import com.arya.browsecityapi.app.CityScoreWrapper;
import com.arya.browsecityapi.infra.ICityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService implements ICityService {

    @Qualifier("jpa")
    private final ICityRepository repository;

    @Override
    public List<CityScoreWrapper> getCitySuggestions(String q, BigDecimal latitude, BigDecimal longitude) {
        List<CityScoreWrapper> res = new ArrayList<>();
        List<City> cities = repository.findAllByName(q);
        return res;
    }
}
