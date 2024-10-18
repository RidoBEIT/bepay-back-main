package com.beIt.controllers;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
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

import com.beIt.entities.SessionTontineTransaction;
import com.beIt.entities.EventCA;
import com.beIt.entities.Membre;
import com.beIt.repositories.SessionTontineRepository;
import com.beIt.repositories.SessionTontineTransactionRepository;
import com.beIt.service.FileService;

import net.minidev.json.JSONObject;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/nosSessionTontineTransaction")
public class SessionTontineTransactionController {
	
	@Autowired
	SessionTontineTransactionRepository repo;

	@Autowired
	UserController userControl;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	MembreController membreControler;
	
	List<String> files = new ArrayList<String>();
	
	private String uploadPath="./images";

	@GetMapping("/list")
	public List<SessionTontineTransaction> getAllApis(){
		return repo.findAll();
	}
	
	@GetMapping("/get/{id}")
	public SessionTontineTransaction getOne(@PathVariable(value="id") Long id) 
	{
	
		SessionTontineTransaction b = null;
		Optional<SessionTontineTransaction> findItem = repo.findById(id);
		if(findItem.isPresent()) {
			b = findItem.get();
		}
		return b;
	}
	
	@GetMapping("/bySessionTontine/{id}")
	public List<SessionTontineTransaction> getEventsByUser(@PathVariable Long id)
	{
		return repo.findBySessionTontineId(id);
	}
	 
	@GetMapping(path="/getPhoto/{photoName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable String photoName) throws Exception {
      return Files.readAllBytes(Paths.get(uploadPath+ "/" + photoName));
    }
		
	
	@Transactional
	@Modifying
	@PostMapping("/save")
	public SessionTontineTransaction create(@RequestBody SessionTontineTransaction sessionTontine) {	
		SessionTontineTransaction stt = repo.save(sessionTontine);
		this.repo.updateTotalCollecte(stt.getSessionTontine().getIdSessionTontine(), stt.getMontant());
		return stt;
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteClientById(@PathVariable Long id) {
		repo.deleteById(id);
	}
	


	@PutMapping("/update/{id}")
	public SessionTontineTransaction update(@PathVariable("id") long id, @RequestBody SessionTontineTransaction item){
		
		/*User user = new User();
		user = item.getUser();
		user = userControl.updateUser(user.getIdUser(), user);*/
		 return repo.findById(id)
			        .map(sessionTontine -> {
			        	sessionTontine.setMembre(item.getMembre());
			        	sessionTontine.setSessionTontine(item.getSessionTontine());
			        	sessionTontine.setTransaction(item.getTransaction());
			        	sessionTontine.setMontant(item.getMontant());		
			        	sessionTontine.setPartenaire(item.getPartenaire());
			          return repo.save(sessionTontine);
			        })
			        .orElseGet(() -> {
			          item.setIdSessionTontineTransaction(id);
			          return repo.save(item);
			        });

	}
}
