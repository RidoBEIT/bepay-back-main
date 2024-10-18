package com.beIt.controllers;


//import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beIt.entities.AuthRequest;
import com.beIt.entities.User;
import com.beIt.repositories.ProfileRepository;
import com.beIt.repositories.UserRepository;
import com.beIt.util.JwtUtil;

import java.sql.Date;

import org.json.simple.JSONObject;


@RestController
@CrossOrigin("*")
public class WelcomeController {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserRepository uRepository;
	
	@Autowired
	private ProfileRepository pRepository;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/")
	public String welcome() {
		
		return "Application est demarrée";
	}
	
	@GetMapping("/getPW")
	public String getMdp() {
		return bCryptPasswordEncoder.encode("admin");
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/authenticate")
	//@Produces()
	public JSONObject generateToken(@RequestBody AuthRequest authrequest) throws Exception {
		
//		System.out.println("LOGIN === >" +authrequest.getLogin());	
//		
//		
//		System.out.println("PASSWORD === >" + bCryptPasswordEncoder.encode(authrequest.getPassword()));
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authrequest.getLogin(), authrequest.getPassword()));
		}
		catch (Exception e) {
			throw new Exception("Invalid login and password");
		}
		
		//System.out.println(authrequest.getUsername());
		JSONObject token = new JSONObject();
		
		User user = (User) uRepository.findByUsername(authrequest.getLogin());
		com.beIt.entities.Profile userPro = user.getProfile_user();
		//System.out.println("PROFILE === >" +userPro); 
		
		//User u = userRepository.findByLogin(authrequest.getLogin());
		String jwt = jwtUtil.generateToken(authrequest.getLogin());		
		token.put("token", jwt);
		token.put("expiresIn", jwtUtil.extractExpiration(jwt));
		token.put("isExpired", jwtUtil.isExpired(jwt));
		token.put("idUser", user.getIdUser());
		token.put("nom", user.getNom());
		token.put("login", user.getUsername());
		token.put("profile", userPro.getNom_profile());
		token.put("idProfile", userPro.getId_profile());
		
		return token;
	
	}

}
