package com.sqli.microservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sqli.microservice.entities.State;

public interface StateRepository extends JpaRepository<State, Integer> {
  
	@Query(value="select state.* from country,state where state.country_id =country.id and code='SN'",nativeQuery = true)
    List<State> findByCountryCode(@Param("code")  String code);


}
