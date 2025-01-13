package com.arya.browsecityapi.app.service;

import com.arya.browsecityapi.app.City;
import com.arya.browsecityapi.app.CityScoreWrapper;
import com.arya.browsecityapi.app.exception.CityParameterException;
import com.arya.browsecityapi.infra.ICityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService implements ICityService {

    @Qualifier("jpa")
    private final ICityRepository repository;

    @Override
    public List<CityScoreWrapper> getCitySuggestions(String q, BigDecimal latitude, BigDecimal longitude) throws CityParameterException {
        List<City> cities = repository.findAllByName(q);

        if (q.isBlank()) {
            throw new CityParameterException("'q' parameter cannot be null");
        } else {
            return cities.stream().map(o -> CityScoreWrapper.builder()
                    .city(o)
                    .score(calculateCitySuggestionScore(o.getName(), o.getLatitude(), o.getLongitude(), q, latitude, longitude, o.getPopulation()))
                    .build()
            ).sorted(Comparator.comparing(CityScoreWrapper::getScore).reversed()).collect(Collectors.toList());
        }
    }

    private Double calculateNameSimilarity(String name1, String name2) {
        name1 = name1.toLowerCase();
        name2 = name2.toLowerCase();
        return (double) (name1.length() - name1.replace(name2, "").length()) / (double) name1.length();
    }

    private Double calculateDistance(double cityLat, double cityLong, double queryLat, double queryLong) {
        double latDistance = Math.toRadians(cityLat - queryLat);
        double lonDistance = Math.toRadians(cityLong - queryLong);
        double a = Math.sin(latDistance / 2.0) * Math.sin(latDistance / 2.0) + Math.cos(Math.toRadians(queryLat)) * Math.cos(Math.toRadians(cityLat)) * Math.sin(lonDistance / 2.0) * Math.sin(lonDistance / 2.0);
        double c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1.0 - a));
        return 6371.0 * c;
    }

    private Double calculatePopulationFactor(long population) {
        long maxPopulation = 10000000L;
        return Math.log((double) (population + 1L)) / Math.log((double) (maxPopulation + 1L));
    }

    private Double calculateCitySuggestionScore(String cityName, BigDecimal cityLat, BigDecimal cityLong, String query, BigDecimal queryLat, BigDecimal queryLong, long population) {
        double TEXT_WEIGHT = 0.5;
        double DISTANCE_WEIGHT = 0.3;
        double POPULATION_WEIGHT = 0.2;
        double textSimilarity = this.calculateNameSimilarity(cityName, query);
        double populationFactor = this.calculatePopulationFactor(population);
        double score;
        if (queryLat != null && queryLong != null) {
            score = this.calculateDistance(cityLat.doubleValue(), cityLong.doubleValue(), queryLat.doubleValue(), queryLong.doubleValue());
            double finalScore = TEXT_WEIGHT * textSimilarity + DISTANCE_WEIGHT * (1.0 - score / 10000.0) + POPULATION_WEIGHT * populationFactor;
            return (Math.round(finalScore * 10.0) / 10.0);
        } else {
            score = 0.8999999999999999 * textSimilarity + POPULATION_WEIGHT * populationFactor;
            return (Math.round(score * 10.0) / 10.0);
        }
    }
}
