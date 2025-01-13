package com.arya.browsecityapi.infra.mapper;

import com.arya.browsecityapi.app.City;
import com.arya.browsecityapi.infra.CityEntity;
import com.arya.browsecityapi.infra.CityEntity.CityEntityBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;

@Component
public class CityJpaMapper implements ICityJpaMapper {

    @Override
    public City toDomainEntity(CityEntity cityEntity) {
        return City.builder()
                .id(String.valueOf(cityEntity.getId()))
                .name(cityEntity.getName())
                .ascii(cityEntity.getAscii())
                .altNames(cityEntity.getAltName() != null
                        ? Arrays.asList(cityEntity.getAltName().split(","))
                        : Collections.emptyList())
                .latitude(cityEntity.getLat())
                .longitude(cityEntity.getLon())
                .population(cityEntity.getPopulation())
                .build();
    }

    @Override
    public CityEntity toJpaEntity(City city) {
        return StringUtils.isEmpty(city.getId()) ? newEntity(city) : mappedEntity(city);
    }

    private CityEntity newEntity(City city) {
        return getDefaultJpaEntity(city).build();
    }

    private CityEntity mappedEntity(City city) {
        return getDefaultJpaEntity(city).id(Long.parseLong(city.getId())).build();
    }

    private static CityEntityBuilder getDefaultJpaEntity(City city) {
        return CityEntity.builder()
                .name(city.getName())
                .ascii(city.getAscii())
                .altName(String.join(",", city.getAltNames()))
                .lat(city.getLatitude())
                .lon(city.getLongitude())
                .population(city.getPopulation());
    }
}
