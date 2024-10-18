package com.beIt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.beIt.entities.User;

public interface UserRepository  extends JpaRepository<User,Long> {

	@RestResource(path="/login")
	User findByUsername(@Param("motcle")String username);
	
	@Query("SELECT COUNT(*) FROM utilisateur u WHERE u.username = ?1 ")
	int getUsernameCount(@Param("username") String username);
	
	@Query(value="SELECT * FROM utilisateur u WHERE u.username = ?1 LIMIT 1 ", nativeQuery=true)
	User getUserByUsername(@Param("username") String username);
}
