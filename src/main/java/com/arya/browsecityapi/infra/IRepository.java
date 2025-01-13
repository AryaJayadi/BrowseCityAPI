package com.arya.browsecityapi.infra;

import com.arya.browsecityapi.app.City;

import java.util.List;
import java.util.Optional;

public interface IRepository {
    List<City> findAllByName(String name);
}
