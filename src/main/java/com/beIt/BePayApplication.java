package com.beIt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BePayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BePayApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestClient() {
		return new RestTemplate();
	}

	
	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}

}
