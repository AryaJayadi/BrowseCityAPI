package com.arya.browsecityapi.infra.repository;

import com.arya.browsecityapi.app.City;
import com.arya.browsecityapi.infra.IRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("jpa")
@RequiredArgsConstructor
public class PostgresCityRepository implements IRepository {

    private final JpaCityRepository jpaCityRepository;

    @Override
    public List<City> findAllByName(String name) {
        return List.of();
    }
}
