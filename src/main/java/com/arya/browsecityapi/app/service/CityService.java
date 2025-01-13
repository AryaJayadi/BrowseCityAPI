package com.arya.browsecityapi.app.service;

import com.arya.browsecityapi.app.CityScoreWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService implements ICityService {
    @Override
    public List<CityScoreWrapper> getCitySuggestions(String q, BigDecimal latitude, BigDecimal longitude) {
        return List.of();
    }
}
