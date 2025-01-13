package com.arya.browsecityapi.api;

import com.arya.browsecityapi.api.model.SuggestCityApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CityController implements CityApi {
    @Override
    public List<SuggestCityApiResponse> getSuggestions(String q, BigDecimal latitude, BigDecimal longitude) {
        return List.of();
    }
}
