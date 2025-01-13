package com.arya.browsecityapi.infra.repository;

import com.arya.browsecityapi.app.City;
import com.arya.browsecityapi.infra.IRepository;
import com.arya.browsecityapi.infra.mapper.CityJpaMapper;
import com.arya.browsecityapi.infra.mapper.ICityJpaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("jpa")
@RequiredArgsConstructor
public class PostgresCityRepository implements IRepository {

    private final JpaCityRepository jpaCityRepository;
    private final ICityJpaMapper mapper = new CityJpaMapper();

    @Override
    public List<City> findAllByName(String name) {
        return jpaCityRepository.findByName(name).map(mapper::toDomainEntity);
    }
}
