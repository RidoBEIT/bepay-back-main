/**
 * 
 */
package com.beIt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.beIt.entities.Parametre;

public interface ParametreRepository extends JpaRepository<Parametre, Long>{

	@Query(value = "SELECT * FROM parametre WHERE api_id_api=?1 AND type=?2 ORDER BY ordre asc", nativeQuery=true)
	public List<Parametre> getParamByApiAndType(@Param(value = "idApi") long idApi, @Param(value = "type") String type);
	
	@Query(value = "SELECT * FROM parametre WHERE api_id_api=?1 AND code_type=?2 AND ordre=?3 LIMIT 1", nativeQuery=true)
	public Parametre getParamByApiAndTypeAndOrdre(@Param(value = "idApi") long idApi, @Param(value = "code_type") int code_type, @Param(value="ordre") int ordre);
	
	
	@Query(value = "SELECT * FROM parametre WHERE api_id_api=?1 ORDER BY type", nativeQuery=true)
	public List<Parametre> getApiParam(@Param(value = "idApi") long idApi);
	
	
	
	@Query(value = "SELECT * FROM parametre WHERE api_id_api=?1 AND has_children=true AND type='RequestBody' ORDER By niveau ASC", nativeQuery=true)
	public List<Parametre> getBodyParent(@Param(value = "idApi") long idApi);
	
	@Query(value = "SELECT * FROM parametre WHERE api_id_api=?1 AND has_children=false AND niveau=1 AND type='RequestBody'", nativeQuery=true)
	public List<Parametre> getBodyNoParentNoChildren(@Param(value = "idApi") long idApi);
	
	@Query(value = "SELECT * FROM parametre WHERE parent_id=?1 AND type='RequestBody' ORDER BY ordre asc", nativeQuery=true)
	public List<Parametre> getBodyChildren(@Param(value = "idP") long idP);
	
	@Query(value = "SELECT * FROM parametre WHERE api_id_api=?1 AND has_children=true AND type='Response'", nativeQuery=true)
	public List<Parametre> getResponseParent(@Param(value = "idApi") long idApi);
	
	@Query(value = "SELECT * FROM parametre WHERE parent_id=?1 AND type='Response' ORDER BY ordre asc", nativeQuery=true)
	public List<Parametre> getResponseChildren(@Param(value = "idP") long idP);
	
	@Query(value = "SELECT * FROM parametre WHERE api_id_api=?1 AND niveau =?2", nativeQuery=true)
	public List<Parametre> getApiParamByNiveau(@Param(value = "idApi") long idApi, @Param(value = "niveau") int niveau);
	
	@Query(value = "SELECT * FROM parametre WHERE api_id_api=?1 AND type='RequestBody'", nativeQuery=true)
	public int getBodyMaxNiveau(@Param(value = "idApi") long idApi);
	
	@Query(value = "SELECT * FROM parametre WHERE api_id_api=?1 AND type='Response'", nativeQuery=true)
	public int getResponseMaxNiveau(@Param(value = "idApi") long idApi);
}
