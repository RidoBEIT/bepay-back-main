package com.beIt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.beIt.entities.Evenement;

public interface EvenementRepository extends JpaRepository<Evenement, Long>{

	@Query(value="SELECT * FROM evenement u WHERE u.user_id_user = ?1 ",nativeQuery = true)
	List<Evenement> getEventsByUser(@Param("id") Long id);
}
