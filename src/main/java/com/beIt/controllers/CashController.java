package com.beIt.controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.multipart.MultipartFile;

import com.beIt.entities.Partenaire;
import com.beIt.entities.PossibleValues;
import com.beIt.entities.ResponseParam;
import com.beIt.entities.Api;
import com.beIt.entities.Cash;
import com.beIt.entities.Parametre;
import com.beIt.repositories.CashRepository;
import com.beIt.repositories.PartenaireRepository;
import com.beIt.service.FileService;

import net.minidev.json.JSONObject;



@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/cash")
public class CashController {
	
	@Autowired
	CashRepository repo;
	
	private final FileService fileService = new FileService();
	
	List<String> files = new ArrayList<String>();
		    
	@GetMapping("/list")
	public List<Cash> getAll(){
		
		return repo.findAll();
		
	}
	
	@GetMapping("/getAllCashIn")
	public List<Cash> getAllCashIn(){
		
		return repo.getAllCashIn();
		
	}
	
	@GetMapping("/getAllCashOut")
	public List<Cash> getAllCashOut(){
		
		return repo.getAllCashOut();
		
	}
	
	
	@GetMapping("/{id}")
	public Cash get(@PathVariable(value="id") Long id) 
	{
	
		Cash b = null;
		Optional<Cash> findItem = repo.findById(id);
		if(findItem.isPresent()) {
			b = findItem.get();
		}
		return b;

	}
	
	@PostMapping("/save")
	public Cash createCash(@RequestBody Cash u) {
		
		return repo.save(u);
	}
	
 
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repo.deleteById(id);
	}
	


	@PutMapping("update/{id}")
	public Cash update(@PathVariable("id") long id, @RequestBody Cash item){
		
		
				Optional<Cash> e = repo.findById(id) ;
				
				if(e.isPresent()) {
					Cash currentC = e.get();
					
					String nom = item.getNom();
					if(nom != null) {
						currentC.setNom(nom);
					}
					
					String numero = item.getNumero();
					if(numero != null) {
						currentC.setNumero(numero);
					}
					
					String prenom = item.getPrenom();
					if(prenom != null) {
						currentC.setPrenom(prenom);
					}
					
					String libelle_decaisser = item.getLibelle();
					if(libelle_decaisser != null) {
						currentC.setLibelle(libelle_decaisser);
					}
					
					String code = item.getCode();
					if(code != null) {
						currentC.setCode(code);
					}
					
					int montant = item.getMontant();
					if(montant != 0) {
						currentC.setMontant(montant);
					}
					repo.save(currentC);
					return currentC;
				} else {
					return null;
				}
		

	}
	
	

}




