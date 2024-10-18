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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beIt.entities.Api;
import com.beIt.entities.Parametre;
import com.beIt.repositories.ApiRepository;
import com.beIt.repositories.ParametreRepository;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/apis")
public class ApiController {

	
	@Autowired
	ApiRepository repo;
	@Autowired
	ParametreRepository paramRepo;
	
	public String managedEntity = "";
	
	@GetMapping("/list")
	public List<Api> getAllApis(){
		
		
		return repo.findAll();
		
	
	}
	
	@GetMapping("/getApi")
	public Api getApi(@RequestParam(value="idC") Long idPartenaire,@RequestParam(value="methode") String methode,@RequestParam(value="idA") Long idA, @RequestParam(value="mEntity") String mEntity){
		return repo.getApi(idPartenaire,methode,idA,mEntity);
	}
	
	@GetMapping("/{id}")
	public Api getApiById(@PathVariable(value="id") Long id) 
	{
	
		Api b = null;
		Optional<Api> findItem = repo.findById(id);
		if(findItem.isPresent()) {
			b = findItem.get();
		}
		return b;

	}
	
	@PostMapping("/save")
	public Api createApi(@RequestBody Api u) {
		
		Collection<Parametre> params = u.getParametres();
		
		Api api = repo.save(u);
		
		for(Parametre param : params)
		{
			param.setApi(api);
			switch (param.getType()) {
			case "Header": 
				param.setCode_type(1);
				break;
			case "Response": 
				param.setCode_type(2);
				break;
			case "RequestBody": 
				param.setCode_type(3);
				break;				
			case "RequestParam": 
				param.setCode_type(4);
				break;
			case "PathVariable": 
				param.setCode_type(5);
				break;
			default:
				break;
			}
			paramRepo.save(param);
		}
		
		return api;
		
	}
	
	@DeleteMapping("delete/{id}")
	public void deleteApiById(@PathVariable Long id) {
		repo.deleteById(id);
	}
	

	@PutMapping("update/{id}")
	public Api itemUpdate(@PathVariable("id") long id, @RequestBody Api item){
		

		 return repo.findById(id)
			        .map(api -> {
			        	api.setLibelle(item.getLibelle());
			        	api.setDescription(item.getDescription());
			        	api.setUrl(item.getUrl());
			        	api.setTypeRetour(item.getTypeRetour());
			        	api.setPartenaire(item.getPartenaire());
			        	api.setAction(item.getAction());
			        	api.setMethode(item.getMethode());
			        	api.setReponse(item.getReponse());
			        	api.setManagedEntity(item.getManagedEntity());
			          return repo.save(api);
			        })
			        .orElseGet(() -> {
			          item.setIdApi(id);
			          return repo.save(item);
			        });
	}

}




