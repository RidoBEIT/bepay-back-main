package com.beIt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.beIt.entities.PossibleValues;
import com.beIt.entities.ResponseParam;

public interface PossibleValueRepository extends JpaRepository<PossibleValues,Long> {

	@Query(value="SELECT * FROM possible_values where value=?1 LIMIT 1", nativeQuery = true)
	PossibleValues getPossibleValue(@Param("value") String value);
	
}
