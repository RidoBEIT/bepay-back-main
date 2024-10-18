package com.beIt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.beIt.entities.Tontine;

public interface TontineRepository extends JpaRepository<Tontine, Long>{

	@Query(value="SELECT * FROM tontine t WHERE t.owner_id_user = ?1 ",nativeQuery = true)
	List<Tontine> getTontinesByUser(@Param("id") Long id);

	@Transactional
	@Modifying
	@Query(value="INSERT INTO membres_tontines (id_tontine, id_membre) VALUES (?1, ?2)", nativeQuery = true)
	void addMembre(Long idTontine, Long idMembre);
}
