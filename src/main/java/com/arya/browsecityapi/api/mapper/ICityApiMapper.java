package com.arya.browsecityapi.api.mapper;

import com.arya.browsecityapi.api.model.SuggestCityApiResponse;
import com.arya.browsecityapi.api.response.SuccessResponse;
import com.arya.browsecityapi.app.CityScoreWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICityApiMapper {
    ResponseEntity<SuccessResponse<List<SuggestCityApiResponse>>> toSuggestCityApiResponse(List<CityScoreWrapper> cityScores);
}
