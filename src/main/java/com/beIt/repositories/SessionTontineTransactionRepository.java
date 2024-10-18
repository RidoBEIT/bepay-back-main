package com.beIt.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.beIt.entities.SessionTontineTransaction;
import com.beIt.entities.Transaction;


public interface SessionTontineTransactionRepository extends JpaRepository<SessionTontineTransaction,Long>{
	@Query(value="SELECT * FROM session_tontine_transaction t WHERE t.session_tontine_id_session_tontine = ?1 ", nativeQuery=true)
	List<SessionTontineTransaction> findBySessionTontineId(long idSessionTontine);
	
	@Modifying
	@Query(value="UPDATE session_tontine_transaction t set statut = ?2 WHERE t.id_session_tontine_transaction = ?1 ", nativeQuery=true)
	void updateStatut(long idTransaction, String newStatut);

	@Modifying
	@Query(value="UPDATE session_tontine t set total_collecte = (total_collecte + ?2) WHERE t.id_session_tontine = ?1 ", nativeQuery=true)
	void updateTotalCollecte(long idSt, int montant);
}	
