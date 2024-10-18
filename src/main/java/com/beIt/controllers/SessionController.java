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
import com.beIt.entities.Partenaire;
import com.beIt.entities.Session;
import com.beIt.repositories.ApiRepository;
import com.beIt.repositories.ParametreRepository;
import com.beIt.repositories.SessionRepository;

import net.minidev.json.JSONObject;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/rest/sessions")
public class SessionController {

	@Autowired
	SessionRepository repo;
	@Autowired
	ParametreRepository paramRepo;
	@Autowired
	AuthenticateController authenticateControl;

	@Autowired
	PartenaireController compagnieControl;
	
	@GetMapping("/list")
	public List<Session> getAll(){
		
		return repo.findAll();
		
	}
	

	@GetMapping("/{id}")
	public Session getById(@PathVariable(value="id") Long id) 
	{
	
		Session b = null;
		Optional<Session> findItem = repo.findById(id);
		if(findItem.isPresent()) {
			b = findItem.get();
		}
		return b;

	}
	
	public void unSetCurrentSession()
	{
		List<Session>  sessions = this.getAll();
		for(Session session : sessions)
		{
			session.setIsCurrent(false);
		}
	}
	@GetMapping("/token/{idPartenaire}")
	public JSONObject getToken(@PathVariable Long idPartenaire)
	{
		Session currentSession = this.getCurrentSession(idPartenaire);
		Boolean isExpire = authenticateControl.isExpired(currentSession.getExpireDate());
		JSONObject object = new JSONObject();
		//System.out.println("isExpire = "+ isExpire);
		if(isExpire==true)
		{
//			
			JSONObject JsonToken = authenticateControl.authenticate(idPartenaire);
			//System.out.println("JSONToken = "+JsonToken);
			 currentSession = this.getCurrentSession(idPartenaire);
			
			object.put("token", currentSession.getToken());
			object.put("type", currentSession.getToken_type());

			return object;
		}
		else {

			object.put("token", currentSession.getToken());
			object.put("type", currentSession.getToken_type());

			return object;
		}
	}
	
	@GetMapping("/currentSession/{idPartenaire}")
	public Session getCurrentSession(@PathVariable Long idPartenaire)
	{
		return repo.getCurrentSession(idPartenaire);
	}
	
	@PostMapping("/save")
	public Session create(@RequestBody Session u) {
		
		return repo.save(u);
		
	}
	
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable Long id) {
		repo.deleteById(id);
	}
	

	

}
