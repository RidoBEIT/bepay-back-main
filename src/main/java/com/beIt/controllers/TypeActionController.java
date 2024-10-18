package com.beIt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beIt.entities.Partenaire;
import com.beIt.entities.TypeAction;
import com.beIt.repositories.TypeActionRepository;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/rest/actions")
public class TypeActionController {
	
	@Autowired
	TypeActionRepository repo;
	
	@GetMapping("/list")
	public List<TypeAction> getAll(){
		
		return repo.findAll();
		
	}

}
