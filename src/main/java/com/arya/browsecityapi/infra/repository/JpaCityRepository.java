package com.arya.browsecityapi.infra.repository;

import com.arya.browsecityapi.infra.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaCityRepository extends JpaRepository<CityEntity, Long> {

    @Query("SELECT c FROM CityEntity c WHERE c.name LIKE :name%")
    List<CityEntity> findAllByName(@Param("name") String name);

}
