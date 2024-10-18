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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beIt.entities.Parametre;
import com.beIt.repositories.ParametreRepository;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/parametres")
public class ParamController {

	
	@Autowired
	ParametreRepository repo;
	
	@GetMapping("/list")
	public List<Parametre> getAll(){
		
		
		return repo.findAll();
		
	
	}
	
	@GetMapping("/api/{idApi}")
	public List<Parametre> getParamByApiAndType(@PathVariable(value="idApi") Long idApi,@RequestParam(value="type") String type)
	{
		return repo.getParamByApiAndType(idApi, type);
	}
	
	@GetMapping("/api/{idApi}/{code_type}/{ordre}")
	public Parametre getParamByApiAndTypeAndOrdre(@PathVariable(value="idApi") Long idApi,@PathVariable(value="code_type") int code_type, @PathVariable int ordre)
	{
		return repo.getParamByApiAndTypeAndOrdre(idApi, code_type, ordre);
	}
	
	@GetMapping("/apiParams/{idApi}")
	public List<Parametre> getApiParams(@PathVariable(value="idApi") Long idApi)
	{
		return repo.getApiParam(idApi);
	}
	
	@GetMapping("/bodyNiveau/{idApi}")
	public int getBodyMaxNiveau(@PathVariable(value="idApi") Long idApi)
	{
		return repo.getBodyMaxNiveau(idApi);
	}
	
	@GetMapping("/responseNiveau/{idApi}")
	public int getResponseMaxNiveau(@PathVariable(value="idApi") Long idApi)
	{
		return repo.getResponseMaxNiveau(idApi);
	}
	
	@GetMapping("/apiParams/{idApi}/{niveau}")
	public List<Parametre> getApiParams(@PathVariable(value="idApi") Long idApi,@PathVariable(value="niveau") int niveau)
	{
		return repo.getApiParamByNiveau(idApi,niveau);
	}
	
	@GetMapping("/apiBodyParent/{idApi}")
	public List<Parametre> getBodyParent(@PathVariable(value="idApi") Long idApi)
	{
		return repo.getBodyParent(idApi);
	}
	
	@GetMapping("/apiBodyNoParentNoChildren/{idApi}")
	public List<Parametre> getBodyNoParentNoChildren(@PathVariable(value="idApi") Long idApi)
	{
		return repo.getBodyNoParentNoChildren(idApi);
	}
	
	@GetMapping("/apiBodyChildren/{idP}")
	public List<Parametre> getBodyChildren(@PathVariable(value="idP") Long idP)
	{
		return repo.getBodyChildren(idP);
	}
	
	
	@GetMapping("/apiResponseParent/{idApi}")
	public List<Parametre> getResponseParent(@PathVariable(value="idApi") Long idApi)
	{
		return repo.getResponseParent(idApi);
	}
	
	@GetMapping("/apiResponseChildren/{idP}")
	public List<Parametre> getResponseChildren(@PathVariable(value="idP") Long idP)
	{
		return repo.getResponseChildren(idP);
	}
	
	@GetMapping("/{id}")
	public Parametre get(@PathVariable(value="id") Long id) 
	{
	
		Parametre b = null;
		Optional<Parametre> findItem = repo.findById(id);
		if(findItem.isPresent()) {
			b = findItem.get();
		}
		return b;

	}
	
	@PostMapping("/save")
	public Parametre create(@RequestBody Parametre u) {
		
		return repo.save(u);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repo.deleteById(id);
	}
	


	@PutMapping("update/{id}")
	public Parametre update(@PathVariable("id") long id, @RequestBody Parametre item){
		
		 return repo.findById(id)
			        .map(param -> {
			        	param.setValue(item.getValue());
			        	param.setKey(item.getKey());
			        	param.setOrdre(item.getOrdre());
			        	param.setApi(item.getApi());
			        	param.setParent(item.getParent());
			        	param.setHas_children(item.getHas_children());
			        	param.setNiveau(item.getNiveau());
			        	param.setDescription(item.getDescription());

						        	
			          return repo.save(param);
			        })
			        .orElseGet(() -> {
			          item.setIdParametre(id);
			          return repo.save(item);
			        });
	}

}




