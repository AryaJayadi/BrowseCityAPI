package com.arya.browsecityapi.infra.repository;

import com.arya.browsecityapi.infra.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaCityRepository extends JpaRepository<CityEntity, Long> {

    List<CityEntity> findAllByName(String name);

}
