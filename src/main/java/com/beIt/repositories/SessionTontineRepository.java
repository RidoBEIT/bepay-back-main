package com.beIt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.beIt.entities.SessionTontine;
import com.beIt.entities.Tontine;

public interface SessionTontineRepository extends JpaRepository<SessionTontine, Long>{

	@Query(value="SELECT * FROM session_tontine st WHERE st.tontine_id_tontine = ?1 ",nativeQuery = true)
	List<SessionTontine> getSessionsTontinesByTontine(@Param("id") Long id);
}
