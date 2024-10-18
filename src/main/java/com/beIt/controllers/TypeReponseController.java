package com.beIt.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beIt.entities.TypeReponse;
import com.beIt.repositories.TypeReponseRepository;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/rest/reponses")
public class TypeReponseController {
	@Autowired
	TypeReponseRepository repo;
	
	@GetMapping("/list")
	public List<TypeReponse> getAll(){
		
		return repo.findAll();
		
	}
	
	@GetMapping("/{id}")
	public TypeReponse get(@PathVariable(value="id") Long id) 
	{
	
		TypeReponse b = null;
		Optional<TypeReponse> findItem = repo.findById(id);
		if(findItem.isPresent()) {
			b = findItem.get();
		}
		return b;

	}
	
	@PostMapping("/save")
	public TypeReponse create(@RequestBody TypeReponse u) {
		
		return repo.save(u);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repo.deleteById(id);
	}
	


	@PutMapping("update/{id}")
	public TypeReponse update(@PathVariable("id") long id, @RequestBody TypeReponse item){
		
		 return repo.findById(id)
			        .map(param -> {
			        	param.setLibelle_typeReponse(item.getLibelle_typeReponse());
			        	param.setType(item.getType());
			          return repo.save(param);
			        })
			        .orElseGet(() -> {
			          item.setIdTypeReponse(id);
			          return repo.save(item);
			        });
	}
}
