/**
 * 
 */
package com.beIt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.beIt.entities.Application;
import com.beIt.entities.Evenement;

public interface AppClientRepository extends JpaRepository<Application, Long>{

	@Query(value="SELECT * FROM client c WHERE c.numero = ?1 LIMIT 1", nativeQuery = true)
	Application getClientByNumber(@Param("numero") Long numero);
	
	@Query(value="SELECT * FROM application u WHERE u.user_id_user = ?1 ",nativeQuery = true)
	List<Application> getAppByUser(@Param("id") Long id);
}
