package com.arya.browsecityapi.api;

import com.arya.browsecityapi.api.mapper.ICityApiMapper;
import com.arya.browsecityapi.api.model.SuggestCityApiResponse;
import com.arya.browsecityapi.app.CityScoreWrapper;
import com.arya.browsecityapi.app.service.ICityService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
public class CityController implements CityApi {

    private final ICityService service;
    private final ICityApiMapper mapper;

    @Override
    public List<SuggestCityApiResponse> getSuggestions(String q, BigDecimal latitude, BigDecimal longitude) {
        List<CityScoreWrapper> cities = service.getCitySuggestions(q, latitude, longitude);
        return mapper.toSuggestCityApiResponse(cities);
    }
}
