package com.beIt.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.beIt.entities.Profile;
import com.beIt.entities.User;
import com.beIt.repositories.ProfileRepository;
import com.beIt.repositories.UserRepository;


@Service
public class UserDetailService  implements UserDetailsService{

	@Autowired()
	UserRepository userRepository;
	
	
	@Autowired
	ProfileRepository profileRepository;

	@Autowired(required=true)
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void saveUserWithDefaultProfile(User user) {
		System.out.println("------- USER -------");
		System.out.println(user);
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		
		user.setPassword(encodedPassword);		
		Profile p = profileRepository.findByProfile("USER");
		
		user.setProfile_user(p);		
		userRepository.save(user);
	}
	
	public User save(User user) {
		System.out.println("------- USER -------");
		System.out.println(user);
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		
		user.setPassword(encodedPassword);		
		return userRepository.save(user);
	}
	
	public List<User> listAllUser(){
		return userRepository.findAll();
	}
	
	public List<Profile> getProfiles(){
		return profileRepository.findAll();
	}
	
	public User get(Long id) {
		return userRepository.findById(id).get();
	}
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(login);
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
		
		
		
	}

}
