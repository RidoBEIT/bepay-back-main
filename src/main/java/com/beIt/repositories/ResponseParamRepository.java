package com.beIt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.beIt.entities.ResponseParam;

public interface ResponseParamRepository extends JpaRepository<ResponseParam,Long> {

	@Query(value="SELECT * FROM reponse_param a WHERE a.id_api =?1 ", nativeQuery = true)
	List<ResponseParam> getByApi(@Param("idApi") Long idApi);
	
	@Query(value="SELECT * FROM reponse_param a WHERE a.id_api =?1 LIMIT 1 ", nativeQuery = true)
	ResponseParam getOneByApi(@Param("idApi") Long idApi);
	
}
