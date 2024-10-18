package com.beIt.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.beIt.entities.Api;
import com.beIt.entities.Decaisser;


public interface DecaisserRepository extends JpaRepository<Decaisser,Long>{
	@Query(value="SELECT * FROM decaisser t WHERE t.evenement_id_evenement = ?1 ", nativeQuery=true)
	List<Decaisser> findByEventId(long idEvent);
	
	@Modifying
	@Query(value="UPDATE decaisser t set statut = ?2 WHERE t.id_decaisser = ?1 ", nativeQuery=true)
	void updateStatut(long idDecaisser, String newStatut);

}	
