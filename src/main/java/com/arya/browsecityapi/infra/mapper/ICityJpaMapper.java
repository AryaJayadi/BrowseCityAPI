package com.arya.browsecityapi.infra.mapper;

import com.arya.browsecityapi.app.City;
import com.arya.browsecityapi.infra.CityEntity;

public interface ICityJpaMapper {

    CityEntity toJpaEntity(City city);

    City toDomainEntity(CityEntity cityEntity);

}
