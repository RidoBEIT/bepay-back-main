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

import com.beIt.entities.Profile;
import com.beIt.entities.User;
import com.beIt.repositories.UserRepository;

@RestController
@RequestMapping("/api/nosUsers")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/list")
	public List<User> getUsers(){
		return userRepo.findAll();
	}
	
	
	@PostMapping("/save")
	public User addUser(@RequestBody User user) {
		System.out.println("user  ="+ user);
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());		
		user.setPassword(encodedPassword);
		return userRepo.save(user);
	}
	
	@PostMapping("/saveClient")
	public User addUserClient(@RequestBody User user) {
//		System.out.println("user  ="+ user);
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());		
		user.setPassword(encodedPassword);
		return userRepo.save(user);
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable(value="id") long idUser){
		User u = null;
		Optional<User> findItem = userRepo.findById(idUser);
		if(findItem.isPresent()) {
			u = findItem.get();
		}
		return u;
	}
	
	@GetMapping("user/{username}")
	public int getUsernamesCount(@PathVariable(value="username") String name ){
		return userRepo.getUsernameCount(name );
	}
	
	@GetMapping("get/{username}")
	public User getUserbyUsername(@PathVariable(value="username") String name ){
		return userRepo.getUserByUsername(name );
	}
	
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable Long id) {
		userRepo.deleteById(id);
	}
	
	@PutMapping("/update/{id}")
	public User updateUser(@PathVariable("id") final Long id, @RequestBody User user) {
		Optional<User> e = userRepo.findById(id) ;
		if(e.isPresent()) {
			User currentU = e.get();
			
			String name = user.getNom() ;
			if(name != null) {
				currentU.setNom(name);
			}
			
			
			String username = user.getUsername() ;
			if(name != null) {
				currentU.setUsername(username);
			}
			
			Boolean statut = user.getStatut();
			if(statut!=null) {
				currentU.setStatut(statut);
			}
			
//			String password = user.getPassword();
//			String encodedPassword = bCryptPasswordEncoder.encode(password);		
//			if(prenom != null) {
//				currentU.setPassword(encodedPassword);
//			}
			
			String email = user.getEmail();
			if(email != null) {
				currentU.setEmail(email);
			}
			
			String teephone = user.getTelephone();
			if(teephone != null) {
				currentU.setTelephone(teephone);
			}
			
			Profile profile = user.getProfile_user();
			if(profile !=null)
			{
				currentU.setProfile_user(profile);
			}
			
			
			
			userRepo.save(currentU);
			return currentU;
		} else {
			return null;
		}
	}
}

