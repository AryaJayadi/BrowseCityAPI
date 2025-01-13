package com.arya.browsecityapi.infra.repository;

import com.arya.browsecityapi.infra.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaCityRepository extends JpaRepository<CityEntity, Long> {

    Optional<CityEntity> findByName(String name);

}
