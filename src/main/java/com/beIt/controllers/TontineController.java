package com.beIt.controllers;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;
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

import com.beIt.entities.Tontine;
import com.beIt.entities.EventCA;
import com.beIt.entities.Membre;
import com.beIt.repositories.TontineRepository;
import com.beIt.service.FileService;

import net.minidev.json.JSONObject;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/nosTontines")
public class TontineController {
	
	@Autowired
	TontineRepository repo;

	@Autowired
	UserController userControl;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	MembreController membreControler;
	
	private final FileService fileService = new FileService();
	
	List<String> files = new ArrayList<String>();
	
	private String uploadPath="./images";

	@GetMapping("/list")
	public List<Tontine> getAllApis(){
		return repo.findAll();
	}
	
	@GetMapping("/get/{id}")
	public Tontine getOne(@PathVariable(value="id") Long id) 
	{
	
		Tontine b = null;
		Optional<Tontine> findItem = repo.findById(id);
		if(findItem.isPresent()) {
			b = findItem.get();
		}
		return b;

	}
	
	@Transactional
	@PostMapping("/addMembre/{idTontine}")
	public Tontine addMembre(@PathVariable(value="idTontine") Long idTontine, @RequestBody Membre membre) 
	{
	
		if(membre.getIdMembre() == null) {
			membre = this.membreControler.saveMembre(membre);
		}
		repo.addMembre(idTontine, membre.getIdMembre());
		return this.getOne(idTontine);
	}
	
	@GetMapping("/user/{id}")
	public List<Tontine> getEventsByUser(@PathVariable Long id)
	{
		return repo.getTontinesByUser(id);
	}
	
	@GetMapping("/byUser/{idUser}")
	public List<EventCA> getAllAgenceCAByAgence(@PathVariable("idUser") Long idUser)
	{
		
        String querySql = "SELECT e.libelle nom, COUNT(*) nombre, SUM(montant) prix FROM evenement e, transaction t WHERE e.id_evenement = t.evenement_id_evenement AND e.user_id_user ="+idUser+" GROUP BY e.libelle ORDER BY nombre DESC"
        		+ "";
        
        
        List<EventCA> xraps = jdbcTemplate.query(
                querySql, 
                new RowMapper<EventCA>() {
                    public EventCA mapRow(ResultSet rs, int rowNum) throws SQLException {
                    	EventCA xrap = new EventCA();
                        xrap.nom = rs.getString(1);
                        xrap.nombre = rs.getInt(2);
                        xrap.prix = rs.getDouble(3);
                        return xrap;
                    }
                }
        );

        return xraps;
	}
	

	
	@PostMapping("/savefile")
    public JSONObject handleFileUpload(@RequestParam("file") MultipartFile file) {
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
	
	@PostMapping("/updateFile")
    public JSONObject updateFileUpload(@RequestParam("oldFileName") String oldFileName, @RequestParam("file") MultipartFile file) {
//	    	System.out.println("ok");
	   System.out.println(oldFileName);
       String message;
       String response = "";
       JSONObject jsonResponse = new JSONObject();
       try {
          try {
        	  response = this.fileService.update(file, oldFileName);
//	          	System.out.println("file ok");
        	  
          } catch (Exception e) {
             throw new RuntimeException("FAIL!");
          }
          
          files.add(file.getOriginalFilename());          
          message = "Successfully updated!";
          jsonResponse.put("message", message);
          jsonResponse.put("fileURL", response);
//	          return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
          return jsonResponse;
       } catch (Exception e) {
          message = "Failed to update file!";
          jsonResponse.put("message", message);
//	          return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(jsonResponse);
          return jsonResponse;
       }
    }
	 
	@GetMapping(path="/getPhoto/{photoName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable String photoName) throws Exception {
      return Files.readAllBytes(Paths.get(uploadPath+ "/" + photoName));
    }
		
	
	@PostMapping("/save")
	public Tontine create(@RequestBody Tontine tontine) {
		/*User user = new User();
		user = client.getUser();
		user = userControl.addUser(user);
		client.setUser(user);*/
		
		return repo.save(tontine);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteClientById(@PathVariable Long id) {
		repo.deleteById(id);
	}
	


	@PutMapping("/update/{id}")
	public Tontine update(@PathVariable("id") long id, @RequestBody Tontine item){
		
		/*User user = new User();
		user = item.getUser();
		user = userControl.updateUser(user.getIdUser(), user);*/
		 return repo.findById(id)
			        .map(tontine -> {
			        	tontine.setLibelle(item.getLibelle());
			        	tontine.setDescription(item.getDescription());
			        	tontine.setLien(item.getLien());
			        	tontine.setBouton(item.getBouton());
			        	tontine.setPhotoUrl(item.getPhotoUrl());
			        	tontine.setMontant(item.getMontant());
			        	tontine.setMembres(item.getMembres());			
			        	tontine.setPeriodicite(item.getPeriodicite());
			          return repo.save(tontine);
			        })
			        .orElseGet(() -> {
			          item.setIdTontine(id);
			          return repo.save(item);
			        });

	}
}
