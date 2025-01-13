package com.arya.browsecityapi.api.mapper;

import com.arya.browsecityapi.api.model.SuggestCityApiResponse;
import com.arya.browsecityapi.api.response.SuccessResponse;
import com.arya.browsecityapi.app.CityScoreWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CityApiMapper implements ICityApiMapper {

    @Override
    public ResponseEntity<SuccessResponse<List<SuggestCityApiResponse>>> toSuggestCityApiResponse(List<CityScoreWrapper> cityScores) {
        List<SuggestCityApiResponse> res = new ArrayList<>();
        for (CityScoreWrapper o : cityScores) {
            res.add(SuggestCityApiResponse.builder()
                    .name(o.getCity().getName())
                    .latitude(o.getCity().getLatitude().toString())
                    .longitude(o.getCity().getLongitude().toString())
                    .score(o.getScore())
                    .build());
        }
        SuccessResponse<List<SuggestCityApiResponse>> body = new SuccessResponse<>(res);
        ResponseEntity<SuccessResponse<List<SuggestCityApiResponse>>> response =new ResponseEntity<>(body, HttpStatus.OK);
        return response;
    }
}
