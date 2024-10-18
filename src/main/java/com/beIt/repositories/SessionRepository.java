package com.beIt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.beIt.entities.Session;

public interface SessionRepository extends JpaRepository<Session,Long> {

	@Query(value="select * from session where is_current = true AND partenaire_id_partenaire=?1 limit 1", nativeQuery = true)
	public Session getCurrentSession(@Param("idCo") Long idCo);
}
