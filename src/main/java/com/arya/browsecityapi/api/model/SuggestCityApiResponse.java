package com.arya.browsecityapi.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SuggestCityApiResponse {

    private String name;
    private String latitude;
    private String longitude;
    private Double score;
}
