package com.sqli.microservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sqli.microservice.entities.City;

@RepositoryRestResource
public interface CityRepository extends JpaRepository<City, Integer> {
	@Query(value="select city.* from city,state where city.state_id= state.id and state.id = 224",nativeQuery = true)
    List<City> findByCities(@Param("stateId")  Long stateId);

}
