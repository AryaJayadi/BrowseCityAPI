package com.arya.browsecityapi.api.mapper;

import com.arya.browsecityapi.api.model.SuggestCityApiResponse;
import com.arya.browsecityapi.app.CityScoreWrapper;

import java.util.List;

public interface ICityApiMapper {
    List<SuggestCityApiResponse> toSuggestCityApiResponse(List<CityScoreWrapper> cityScores);
}
