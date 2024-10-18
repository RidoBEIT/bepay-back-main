package com.beIt.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.beIt.entities.Transaction;


public interface TransactionRepository extends JpaRepository<Transaction,Long>{
	@Query(value="SELECT * FROM transaction t WHERE t.evenement_id_evenement = ?1 ", nativeQuery=true)
	List<Transaction> findByEventId(long idEvent);
	
	@Modifying
	@Query(value="UPDATE transaction t set statut = ?2 WHERE t.id_transaction = ?1 ", nativeQuery=true)
	void updateStatut(long idTransaction, String newStatut);
	
	@Query(value="SELECT * FROM transaction t WHERE t.type_transaction_id_type_transaction = 1", nativeQuery=true)
	List<Transaction> findByTransactionTypePaiement();
	
	@Query(value="SELECT * FROM transaction t WHERE t.type_transaction_id_type_transaction = 2", nativeQuery=true)
	List<Transaction> findByTransactionTypeRemboursement();
}	
