package com.beIt.controllers;

import java.util.Collection;
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

import com.beIt.entities.Api;
import com.beIt.entities.Parametre;
import com.beIt.entities.PossibleValues;
import com.beIt.entities.ResponseParam;
import com.beIt.repositories.PossibleValueRepository;
import com.beIt.repositories.ResponseParamRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/responseParam")
public class ResponseParamController {

	@Autowired
	ResponseParamRepository repo;
	@Autowired
	PossibleValueRepository pvRepo;
	
	@GetMapping("/list")
	public List<ResponseParam> getAll()
	{
		return repo.findAll();
	}
	
	@GetMapping("/list/{idApi}")
	public List<ResponseParam> getListByApi(@PathVariable Long idApi)
	{
		return repo.getByApi(idApi);
	}
	
	@GetMapping("/{id}")
	public ResponseParam get(@PathVariable(value="id") long id){
		ResponseParam u = null;
		Optional<ResponseParam> findItem = repo.findById(id);
		if(findItem.isPresent()) {
			u = findItem.get();
		}
		return u;
	}
	
	@PostMapping("/save")
	public ResponseParam add(@RequestBody ResponseParam p) {
		//System.out.println("p = "+p.toString());	
//		Collection<PossibleValues> params = p.getPossible_values();
//		System.out.println("params = "+params);
		
		ResponseParam rp = repo.save(p);
		
//		for(PossibleValues param : params)
//		{
//			param.setResponse_param(rp);
//			pvRepo.save(param);
//		}
		
		return rp;	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		repo.deleteById(id);
	}
	
	@PutMapping("/update/{id}")
	public ResponseParam update(@PathVariable("id") final Long id, @RequestBody ResponseParam rp) {
		Optional<ResponseParam> e = repo.findById(id) ;
		if(e.isPresent()) {
			ResponseParam currentP = e.get();
			
			String key = rp.getKey();
			if(key != null) {
				currentP.setKey(key);
			}
			String value = rp.getValue();
			if(value != null) {
				currentP.setValue(value);
			}
			
//			Collection<PossibleValues> ps = rp.getPossible_values();
//			if(value != null)
//			{
//				currentP.setPossible_values(ps);
//			}
			
			
			
			repo.save(currentP);
			return currentP;
		} else {
			return null;
		}
	}

	
}
