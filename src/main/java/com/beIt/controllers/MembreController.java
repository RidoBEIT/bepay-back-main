package com.beIt.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beIt.entities.Membre;
import com.beIt.repositories.MembreRepository;

@RestController
@RequestMapping("/api/nosMembres")
@CrossOrigin(origins = "*")
public class MembreController {
	MembreRepository membreRepo;
	public MembreController(MembreRepository membreRepo) {
		this.membreRepo = membreRepo;
	}


	@GetMapping("/list")
	public List<Membre> getMembres(){
		return membreRepo.findAll();
	}
	
	
	@PostMapping("/save")
	public Membre saveMembre(@RequestBody Membre membre) {
		int min = 1000;
		int max = 9999;
		int code = (int)(Math.random()*(max-min+1)+min);
		membre.setCode(code);
		return membreRepo.save(membre);
	}
	
	@PostMapping("/saveClient")
	public Membre addMembreClient(@RequestBody Membre membre) {
		return membreRepo.save(membre);
	}
	
	@GetMapping("/{id}")
	public Membre getMembreById(@PathVariable(value="id") long idMembre){
		Membre u = null;
		Optional<Membre> findItem = membreRepo.findById(idMembre);
		if(findItem.isPresent()) {
			u = findItem.get();
		}
		return u;
	}
	
	@GetMapping("/getMembreByCode/{code}")
	public Membre getMembrebyCodeAndTelephone(@PathVariable(value="code") int code, String telephone ){
		return membreRepo.getMembreByCode(code );
	}
	
	@DeleteMapping("/{id}")
	public void deleteMembreById(@PathVariable Long id) {
		membreRepo.deleteById(id);
	}
	
	@PutMapping("/update/{id}")
	public Membre updateMembre(@PathVariable("id") final Long id, @RequestBody Membre membre) {
		Optional<Membre> e = membreRepo.findById(id) ;
		if(e.isPresent()) {
			Membre currentU = e.get();
			
			String name = membre.getNom() ;
			if(name != null) {
				currentU.setNom(name);
			}
			
			String prenom = membre.getPrenom() ;
			if(prenom != null) {
				currentU.setNom(prenom);
			}
			
			
			int code = membre.getCode() ;
			if(name != null) {
				currentU.setCode(code);
			}
			
			Boolean statut = membre.getStatut();
			if(statut!=null) {
				currentU.setStatut(statut);
			}
			
			String email = membre.getEmail();
			if(email != null) {
				currentU.setEmail(email);
			}
			
			String telephone = membre.getTelephone();
			if(telephone != null) {
				currentU.setTelephone(telephone);
			}		
			
			
			membreRepo.save(currentU);
			return currentU;
		} else {
			return null;
		}
	}
}

