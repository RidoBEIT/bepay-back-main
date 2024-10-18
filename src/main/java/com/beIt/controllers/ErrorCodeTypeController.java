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

import com.beIt.entities.ErrorCodeType;
import com.beIt.repositories.ErrorCodeTypeRepository;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/errorCodeType")
public class ErrorCodeTypeController {
	@Autowired
	ErrorCodeTypeRepository repo;
	
	@GetMapping("/list")
	public List<ErrorCodeType> getAll(){
		
		return repo.findAll();
		
	}
	
	@GetMapping("/{id}")
	public ErrorCodeType get(@PathVariable(value="id") Long id) 
	{
	
		ErrorCodeType b = null;
		Optional<ErrorCodeType> findItem = repo.findById(id);
		if(findItem.isPresent()) {
			b = findItem.get();
		}
		return b;

	}
	
	@PostMapping("/save")
	public ErrorCodeType create(@RequestBody ErrorCodeType u) {
		
		return repo.save(u);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		repo.deleteById(id);
	}
	


	@PutMapping("update/{id}")
	public ErrorCodeType update(@PathVariable("id") long id, @RequestBody ErrorCodeType item){
		
		 return repo.findById(id)
			        .map(param -> {
			        	param.setLibelle_type_error(item.getLibelle_type_error());
			        	param.setDescription(item.getDescription());
			        	param.setPartenaire(item.getPartenaire());
			          return repo.save(param);
			        })
			        .orElseGet(() -> {
			          item.setIdErrorCodeType(id);
			          return repo.save(item);
			        });
	}
}
