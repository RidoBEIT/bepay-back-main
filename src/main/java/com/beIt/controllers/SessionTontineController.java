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

import com.beIt.entities.SessionTontine;
import com.beIt.entities.EventCA;
import com.beIt.entities.Membre;
import com.beIt.repositories.SessionTontineRepository;
import com.beIt.service.FileService;

import net.minidev.json.JSONObject;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/nosSessionsTontine")
public class SessionTontineController {
	
	@Autowired
	SessionTontineRepository repo;

	@Autowired
	UserController userControl;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	MembreController membreControler;
	
	List<String> files = new ArrayList<String>();
	
	private String uploadPath="./images";

	@GetMapping("/list")
	public List<SessionTontine> getAllApis(){
		return repo.findAll();
	}
	
	@GetMapping("/get/{id}")
	public SessionTontine getOne(@PathVariable(value="id") Long id) 
	{
	
		SessionTontine b = null;
		Optional<SessionTontine> findItem = repo.findById(id);
		if(findItem.isPresent()) {
			b = findItem.get();
		}
		return b;

	}
	
	@GetMapping("/byTontine/{id}")
	public List<SessionTontine> getEventsByUser(@PathVariable Long id)
	{
		return repo.getSessionsTontinesByTontine(id);
	}
	 
	@GetMapping(path="/getPhoto/{photoName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable String photoName) throws Exception {
      return Files.readAllBytes(Paths.get(uploadPath+ "/" + photoName));
    }
		
	
	@PostMapping("/save")
	public SessionTontine create(@RequestBody SessionTontine sessionTontine) {		
		return repo.save(sessionTontine);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteClientById(@PathVariable Long id) {
		repo.deleteById(id);
	}
	


	@PutMapping("/update/{id}")
	public SessionTontine update(@PathVariable("id") long id, @RequestBody SessionTontine item){
		
		/*User user = new User();
		user = item.getUser();
		user = userControl.updateUser(user.getIdUser(), user);*/
		 return repo.findById(id)
			        .map(sessionTontine -> {
			        	sessionTontine.setLibelle(item.getLibelle());
			        	sessionTontine.setDescription(item.getDescription());
			        	sessionTontine.setDateDebut(item.getDateDebut());
			        	sessionTontine.setDateFin(item.getDateFin());
			        	sessionTontine.setMontant(item.getMontant());		
			        	sessionTontine.setTontine(item.getTontine());
			          return repo.save(sessionTontine);
			        })
			        .orElseGet(() -> {
			          item.setIdSessionTontine(id);
			          return repo.save(item);
			        });

	}
}
