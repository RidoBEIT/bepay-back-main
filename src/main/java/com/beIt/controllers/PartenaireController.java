package com.beIt.controllers;


import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;

import com.beIt.entities.Partenaire;
import com.beIt.repositories.PartenaireRepository;
import com.beIt.service.FileService;

import net.minidev.json.JSONObject;



@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/partenaires")
public class PartenaireController {
	
	@Autowired
	PartenaireRepository repo;
	
	private final FileService fileService = new FileService();
	
	List<String> files = new ArrayList<String>();
		    
	@GetMapping("/list")
	public List<Partenaire> getAll(){
		
		return repo.findAll();
		
	}
	
	@GetMapping("/{id}")
	public Partenaire get(@PathVariable(value="id") Long id) 
	{
	
		Partenaire b = null;
		Optional<Partenaire> findItem = repo.findById(id);
		if(findItem.isPresent()) {
			b = findItem.get();
		}
		return b;

	}
	
	@PostMapping("/save")
	public Partenaire create(@RequestBody Partenaire u) {
		
		return repo.save(u);
	}
	
 
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repo.deleteById(id);
	}
	


	@PutMapping("update/{id}")
	public Partenaire update(@PathVariable("id") long id, @RequestBody Partenaire item){
		
		
				Optional<Partenaire> e = repo.findById(id) ;
				
				if(e.isPresent()) {
					Partenaire currentC = e.get();
					
					String nom = item.getNom_partenaire();
					if(nom != null) {
						currentC.setNom_partenaire(nom);
					}
					
					String tel = item.getTelephone();
					if(tel != null) {
						currentC.setTelephone(tel);
					}
					
					String adr = item.getAdressePhysique();
					if(adr != null) {
						currentC.setAdressePhysique(adr);
					}
					
					String email = item.getEmail();
					if(email != null) {
						currentC.setEmail(email);
					}
					
					String rc = item.getRccm();
					if(rc != null) {
						currentC.setRccm(rc);
					}
					
					String nif = item.getNif();
					if(nif != null) {
						currentC.setNif(nif);
					}
					
					String login = item.getLogin();
					if(login != null)
					{
						currentC.setLogin(login);
					}
					
					String password = item.getPassword();
					if(password != null)
					{
						currentC.setPassword(password);
					}
					
					String baseUrl = item.getBaseUrl();
					if(baseUrl != null)
					{
						currentC.setBaseUrl(baseUrl);
					}
					
					if(item.getLogo() != null && !item.getLogo().equals(""));
					{
						currentC.setLogo(item.getLogo());
					}				
					
					
					repo.save(currentC);
					return currentC;
				} else {
					return null;
				}
		

	}
	
	@PostMapping("/savefile")
    public JSONObject saveFile(@RequestParam("file") MultipartFile file) {
//	    	System.out.println("ok");
//	    	System.out.println(file);
       String message;
       String response = "";
       JSONObject jsonResponse = new JSONObject();
       try {
          try {
        	  response = this.fileService.save(file);
//	          	System.out.println("file ok");

        	  
          } catch (Exception e) {
             throw new RuntimeException("FAIL!");
          }
          
          files.add(file.getOriginalFilename());          
          message = "Successfully uploaded!";
          jsonResponse.put("message", message);
          jsonResponse.put("fileURL", response);
//	          return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
          return jsonResponse;
       } catch (Exception e) {
          message = "Failed to upload!";
          jsonResponse.put("message", message);
//	          return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(jsonResponse);
          return jsonResponse;
       }
    }
	
	
	@PostMapping("/updateFile/{id}")
    public JSONObject updateFileUpload(@PathVariable("id") long id, @RequestParam("file") MultipartFile file) {
//	    	System.out.println("ok");
		
		JSONObject jsonResponse = new JSONObject();
		String message;
	    String response = "";
		Optional<Partenaire> op = repo.findById(id) ;
		if(op.isPresent()) {
			Partenaire currentC = op.get();
			String oldFileName = currentC.getLogo();
			
			JSONObject resp = this.saveFile(file);
			//On Supprime l'ancienne photo
			if(currentC.getLogo() != null && !currentC.getLogo().equals("")) {
				this.fileService.delete(currentC.getLogo());
			}
			return resp;
		}
		else {
			 message = "Partenaire missed";
	         jsonResponse.put("message", message);
			return jsonResponse;
		}
//		if(op.isPresent()) {
//			Partenaire currentC = op.get();
//			String oldFileName = currentC.getLogo();
//	       try {
//	          try {
//	        	  response = this.fileService.update(file, oldFileName);
//			      System.out.println("file ok");
//	          } catch (Exception e) {
//	        	  System.out.println("WRONNNGR");
//	             throw new RuntimeException("FAIL!");
//	          }
//	          
//	          files.add(file.getOriginalFilename());          
//	          message = "Successfully updated!";
//	          jsonResponse.put("message", message);
//	          jsonResponse.put("fileURL", response);
////			          return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
//	          return jsonResponse;
//	       } 
//	       catch (Exception e) {
//	          message = "Failed to update file!";
//	          jsonResponse.put("message", message);
//	          System.out.println("Failed to update file!");
////			          return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(jsonResponse);
//	          return jsonResponse;
//	       }
//		}
//		else {
//			 message = "Partenaire missed";
//	         jsonResponse.put("message", message);
//			return jsonResponse;
//		}
    }
}




