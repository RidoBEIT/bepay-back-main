package com.beIt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beIt.entities.Profile;
import com.beIt.entities.TypeAction;
import com.beIt.entities.TypeTransaction;
import com.beIt.repositories.TypeActionRepository;
import com.beIt.repositories.TypeTransactionRepository;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/typeTransactions")
public class TypeTransactionController {

	@Autowired
	TypeTransactionRepository repo;
	
	@GetMapping("/list")
	public List<TypeTransaction> getAll(){
		
		return repo.findAll();
		
	}
	
	@PostMapping("/save")
	public TypeTransaction add(@RequestBody TypeTransaction p) {
						
		return repo.save(p);
	}

}
