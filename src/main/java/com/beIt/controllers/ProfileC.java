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
import org.springframework.web.bind.annotation.RestController;

import com.beIt.entities.Profile;
import com.beIt.repositories.ProfileRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("")
public class ProfileC {

	@Autowired
	ProfileRepository profileRepo;
	
	@GetMapping("/api/profiles")
	public List<Profile> getProfiles()
	{
		return profileRepo.findAll();
	}
	
	
	@GetMapping("/api/listProfile")
	public List<Profile> getAll()
	{
		return profileRepo.findAll();
	}
	
	@GetMapping("/api/profiles/getProfileClient")
	public Profile getProfileClient(){
		return profileRepo.getProfileClient("Client");
	}
	
	@GetMapping("/api/profiles/{id}")
	public Profile getProfileById(@PathVariable(value="id") long id){
		Profile u = null;
		Optional<Profile> findItem = profileRepo.findById(id);
		if(findItem.isPresent()) {
			u = findItem.get();
		}
		return u;
	}
	
	@PostMapping("/api/profiles")
	public Profile addClient(@RequestBody Profile p) {
						
		return profileRepo.save(p);
	}
	
	@DeleteMapping("/api/profiles/{id}")
	public void deleteProfileById(@PathVariable Long id) {
		profileRepo.deleteById(id);
	}
	
	@PutMapping("/api/profiles/{id}")
	public Profile updateProfile(@PathVariable("id") final Long id, @RequestBody Profile profile) {
		Optional<Profile> e = profileRepo.findById(id) ;
		if(e.isPresent()) {
			Profile currentP = e.get();
			
			String name = profile.getNom_profile();
			if(name != null) {
				currentP.setNom_profile(name);
			}
			
			
			profileRepo.save(currentP);
			return currentP;
		} else {
			return null;
		}
	}

	
}


