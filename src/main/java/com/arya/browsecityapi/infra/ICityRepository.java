package com.arya.browsecityapi.infra;

import com.arya.browsecityapi.app.City;

import java.util.List;

public interface ICityRepository {
    List<City> findAllByName(String name);
}
