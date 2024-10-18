package com.beIt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.beIt.entities.Profile;

public interface ProfileRepository extends JpaRepository<Profile,Long> {

	@Query("SELECT p FROM Profile p WHERE p.nom_profile = ?1")
	public Profile findByProfile(String name);
	
	@Query("SELECT p FROM Profile p WHERE p.id_profile = ?1")
	public Profile getById(Long id);
	
	
	@Query("SELECT p FROM Profile p WHERE p.nom_profile = ?1")
	public Profile getProfileClient(String libelle);
}
