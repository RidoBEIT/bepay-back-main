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

import com.beIt.entities.PossibleValues;
import com.beIt.entities.ResponseParam;
import com.beIt.repositories.PossibleValueRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/possibleValue")
public class PossibleValueController {
	@Autowired
	PossibleValueRepository repo;
	
	@GetMapping("/list")
	public List<PossibleValues> getProfiles()
	{
		return repo.findAll();
	}
	
	
	
	@GetMapping("/{id}")
	public PossibleValues getProfileById(@PathVariable(value="id") long id){
		PossibleValues u = null;
		Optional<PossibleValues> findItem = repo.findById(id);
		if(findItem.isPresent()) {
			u = findItem.get();
		}
		return u;
	}
	
	@PostMapping("/save")
	public PossibleValues addClient(@RequestBody PossibleValues p) {
						
		return repo.save(p);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteProfileById(@PathVariable Long id) {
		repo.deleteById(id);
	}
	
	@PutMapping("/update/{id}")
	public PossibleValues updateProfile(@PathVariable("id") final Long id, @RequestBody PossibleValues PossibleValues) {
		Optional<PossibleValues> e = repo.findById(id) ;
		if(e.isPresent()) {
			PossibleValues currentP = e.get();
			
			String msg = PossibleValues.getMessage();
			if(msg != null) {
				currentP.setMessage(msg);
			}
			
			String value = PossibleValues.getValue();
			if(value != null) {
				currentP.setValue(value);
			}
			
			ResponseParam rp = PossibleValues.getResponse_param();
			if(rp!=null)
			{
				currentP.setResponse_param(rp);
			}
			
			
			repo.save(currentP);
			return currentP;
		} else {
			return null;
		}
	}

	
}
