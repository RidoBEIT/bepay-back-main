package com.beIt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.beIt.entities.TypeTransaction;

public interface TypeTransactionRepository extends JpaRepository<TypeTransaction,Long>{

	@Query(value="SELECT * FROM type_transaction t WHERE t.id_type_transaction = 2 LIMIT 1 ", nativeQuery=true)
	TypeTransaction findTypeRefund();
	
}
