package com.beIt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.beIt.entities.Membre;

public interface MembreRepository  extends JpaRepository<Membre,Long> {
	
//	@Query("SELECT * FROM membre m WHERE m.code = ?1 AND m.telephone = ?2 ")
//	Membre getMembreByCodeAndTelephone(@Param("code") int code, @Param("telephone") String telephone);
	
	@Query(value="SELECT * FROM membre m WHERE m.code = ?1 LIMIT 1 ", nativeQuery=true)
	Membre getMembreByCode(int code);
}
