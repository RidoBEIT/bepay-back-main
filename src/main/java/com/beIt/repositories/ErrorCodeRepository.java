package com.beIt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.beIt.entities.ErrorCode;

public interface ErrorCodeRepository extends JpaRepository<ErrorCode,Long>{
	
}
