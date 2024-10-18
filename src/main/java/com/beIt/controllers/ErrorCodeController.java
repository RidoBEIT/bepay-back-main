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

import com.beIt.entities.ErrorCode;
import com.beIt.repositories.ErrorCodeRepository;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/errorCode")
public class ErrorCodeController {
	@Autowired
	ErrorCodeRepository repo;
	
	@GetMapping("/list")
	public List<ErrorCode> getAll(){
		
		return repo.findAll();
		
	}
	
	@GetMapping("/{id}")
	public ErrorCode get(@PathVariable(value="id") Long id) 
	{
	
		ErrorCode b = null;
		Optional<ErrorCode> findItem = repo.findById(id);
		if(findItem.isPresent()) {
			b = findItem.get();
		}
		return b;

	}
	
	@PostMapping("/save")
	public ErrorCode create(@RequestBody ErrorCode u) {
		
		return repo.save(u);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		repo.deleteById(id);
	}
	


	@PutMapping("update/{id}")
	public ErrorCode update(@PathVariable("id") long id, @RequestBody ErrorCode item){
		
		 return repo.findById(id)
			        .map(param -> {
			        	param.setCode(item.getCode());
			        	param.setDescription(item.getDescription());
			          return repo.save(param);
			        })
			        .orElseGet(() -> {
			          item.setIdErrorCode(id);
			          return repo.save(item);
			        });
	}
}
