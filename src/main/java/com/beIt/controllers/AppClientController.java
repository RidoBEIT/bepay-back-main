package com.beIt.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beIt.entities.Application;
import com.beIt.entities.Evenement;
import com.beIt.entities.EventCA;
import com.beIt.entities.User;
import com.beIt.repositories.AppClientRepository;



@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/clients")
public class AppClientController {
	
	@Autowired
	AppClientRepository repo;
	
	@Autowired
	UserController userControl;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/list")
	public List<Application> getAllApis(){
		
		
		return repo.findAll();
		
	
	}
	
	@GetMapping("/{id}")
	public Application getClientById(@PathVariable(value="id") Long id) 
	{
	
		Application b = null;
		Optional<Application> findItem = repo.findById(id);
		if(findItem.isPresent()) {
			b = findItem.get();
		}
		return b;

	}
	
	@GetMapping("/user/{id}")
	public List<Application> getAppByUser(@PathVariable Long id)
	{
		return repo.getAppByUser(id);
	}
	
	@GetMapping("all/apps/{idUser}")
	public List<EventCA> getAllAgenceCAByAgence(@PathVariable("idUser") Long idUser)
	{
		
        String querySql = "SELECT a.nom nom, COUNT(*) nombre, SUM(montant) prix FROM app_client a, transaction t WHERE a.id = t.app_client_id AND a.user_id_user ="+idUser+" GROUP BY a.nom ORDER BY nombre DESC"
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
	

	
//	@GetMapping("/client/{numero}")
//	public AppClient getClientByNumber(@PathVariable(value="numero") Long numero)
//	{
//		return repo.getClientByNumber(numero);
//	}
	
	@PostMapping("/save")
	public Application create(@RequestBody Application client) {
		return repo.save(client);
	}
	
	@PostMapping("/create")
	public Application createbyUser(@RequestBody Application client) {		
		UUID uuid = UUID.randomUUID();
		
		return repo.save(client);
	}
	
	@DeleteMapping("/{id}")
	public void deleteClientById(@PathVariable Long id) {
		repo.deleteById(id);
	}
	


	@PutMapping("update/{id}")
	public Application update(@PathVariable("id") long id, @RequestBody Application item){
		User user = new User();
		user = item.getUser();
		user = userControl.updateUser(user.getIdUser(), user);
		 return repo.findById(id)
			        .map(app -> {
			        	app.setNom(item.getNom());
			        	app.setDescription(item.getDescription());
			          return repo.save(app);
			        })
			        .orElseGet(() -> {
			          item.setId(id);
			          return repo.save(item);
			        });

	}
}
