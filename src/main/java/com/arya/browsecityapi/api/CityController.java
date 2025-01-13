package com.arya.browsecityapi.api;

import com.arya.browsecityapi.api.mapper.ICityApiMapper;
import com.arya.browsecityapi.api.model.SuggestCityApiResponse;
import com.arya.browsecityapi.api.response.SuccessResponse;
import com.arya.browsecityapi.app.CityScoreWrapper;
import com.arya.browsecityapi.app.exception.CityParameterException;
import com.arya.browsecityapi.app.service.ICityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<SuccessResponse<List<SuggestCityApiResponse>>> getSuggestions(String q, BigDecimal latitude, BigDecimal longitude) throws CityParameterException {
        return mapper.toSuggestCityApiResponse(service.getCitySuggestions(q, latitude, longitude));
    }
}
