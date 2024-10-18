package com.beIt.controllers;
//package com.beIt.Controller;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.beIt.Entity.Api;
//import com.beIt.Entity.Response;
//import com.beIt.repository.ResponseRepository;
//
//@CrossOrigin("*")
//@RestController
//@RequestMapping("/api/response")
//public class ResponseController {
//	@Autowired
//	ResponseRepository repo;
//	
//	@GetMapping("/list")
//	public List<Response> getProfiles()
//	{
//		return repo.findAll();
//	}
//	
//	
//	
//	@GetMapping("/{id}")
//	public Response getProfileById(@PathVariable(value="id") long id){
//		Response u = null;
//		Optional<Response> findItem = repo.findById(id);
//		if(findItem.isPresent()) {
//			u = findItem.get();
//		}
//		return u;
//	}
//	
//	@PostMapping("/save")
//	public Response addClient(@RequestBody Response p) {
//						
//		return repo.save(p);
//	}
//	
//	@DeleteMapping("/delete/{id}")
//	public void deleteProfileById(@PathVariable Long id) {
//		repo.deleteById(id);
//	}
//	
//	@PutMapping("/update/{id}")
//	public Response updateProfile(@PathVariable("id") final Long id, @RequestBody Response rs) {
//		Optional<Response> e = repo.findById(id) ;
//		if(e.isPresent()) {
//			Response currentP = e.get();
//			
////			String body = rs.getBody();
////			if(body != null) {
////				currentP.setBody(body);
////			}
//
//			
//			
//			repo.save(currentP);
//			return currentP;
//		} else {
//			return null;
//		}
//	}
//
//	
//}
