package com.arya.browsecityapi.api;

import com.arya.browsecityapi.api.model.SuggestCityApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@RequestMapping("/city")
public interface CityApi {
    @Operation(
            summary = "Search for cities based on a query",
            description = "Returns city suggestions based on partial or complete name matching, optionally considering proximity to the provided latitude and longitude for better scoring",
            parameters = {
                    @Parameter(name = "q", description = "Partial or complete search term", required = false),
                    @Parameter(name = "latitude", description = "Latitude of the caller for proximity scoring", required = false),
                    @Parameter(name = "longitude", description = "Longitude of the caller for proximity scoring", required = false)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Suggestions returned successfully"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            }
    )
    @GetMapping("/suggestions")
    List<SuggestCityApiResponse> getSuggestions(
            @RequestParam(value = "q", required = false, defaultValue = "") String q,
            @RequestParam(value = "latitude", required = false) BigDecimal latitude,
            @RequestParam(value = "longitude", required = false) BigDecimal longitude
    );
}
