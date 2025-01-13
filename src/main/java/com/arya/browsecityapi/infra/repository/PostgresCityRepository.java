package com.arya.browsecityapi.infra.repository;

import com.arya.browsecityapi.app.City;
import com.arya.browsecityapi.infra.CityEntity;
import com.arya.browsecityapi.infra.ICityRepository;
import com.arya.browsecityapi.infra.mapper.CityJpaMapper;
import com.arya.browsecityapi.infra.mapper.ICityJpaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("jpa")
@RequiredArgsConstructor
public class PostgresCityRepository implements ICityRepository {

    private final JpaCityRepository jpaCityRepository;
    private final ICityJpaMapper mapper = new CityJpaMapper();

    @Override
    public List<City> findAllByName(String name) {
        List<CityEntity> cities = jpaCityRepository.findAllByName(name);
        return cities.stream().map(mapper::toDomainEntity).toList();
    }
}
