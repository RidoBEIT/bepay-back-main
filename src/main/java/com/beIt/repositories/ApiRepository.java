/**
 * 
 */
package com.beIt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.beIt.entities.Api;

public interface ApiRepository extends JpaRepository<Api, Long>{

	@Query(value="SELECT * FROM api a WHERE a.partenaire_id_partenaire =?1 AND a.methode =?2 AND a.action_id_type_action =?3 AND a.managed_entity =?4", nativeQuery = true)
	Api getApi(@Param("idPartenaire") Long idPartenaire, @Param("methode") String methode, @Param("idA") Long idA, @Param("mEntity") String mEntity);
	
	
	@Query(value="SELECT * FROM api WHERE url LIKE CONCAT('%', ?1, '%')", nativeQuery=true)
	Api findApiByUrlContains(String url);

}
