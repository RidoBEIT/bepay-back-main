/**
 * 
 */
package com.beIt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.beIt.entities.Cash;


public interface CashRepository extends JpaRepository<Cash, Long>{
	//@Query(value="SELECT * FROM cash where type_cash=?1 LIMIT 1", nativeQuery = true)
	//Cash getAllCashIn(@Param("typeCash") String value);
	
	//@Query(value="SELECT * FROM cash where type_cash=?1 LIMIT 1", nativeQuery = true)
	//List<Cash> getAllCashOut(@Param("typeCash") String value);
	
	@Query(value="SELECT * FROM cash WHERE type_cash = 'CASH IN'", nativeQuery=true)
	List<Cash> getAllCashIn();
	
	@Query(value="SELECT * FROM cash WHERE type_cash = 'CASH OUT'", nativeQuery=true)
	List<Cash> getAllCashOut();
	
	@Modifying
	@Query(value="UPDATE cash set statut = ?2 WHERE id_cash = ?1 ", nativeQuery=true)
	void updateStatut(long idCash, String newStatut);
}
