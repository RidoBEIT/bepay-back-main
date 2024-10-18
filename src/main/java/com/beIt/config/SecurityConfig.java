package com.beIt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.beIt.filter.JwtFilter;
import com.beIt.service.UserDetailService;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtFilter jwtFilter;
	
	@Autowired
	private UserDetailService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
//	@Bean
//	public PasswordEncoder passwordEncoder()
//	{
//		return new BCryptPasswordEncoder();
//	}
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
  @Override
	protected void configure(HttpSecurity http) throws Exception {
	  http.cors();
	 http.csrf().disable().authorizeRequests()
	 .antMatchers("/authenticate",
			 	"/api/nosUsers/saveClient",
			 	"/api/profiles/getProfileClient",
				"/api/partenaires/list*", 
				"/api/evenement/get/*",
				"/api/partenaires/list",
				"/api/evenement/getPhoto/*",
				"/api/transaction/saveTransaction/*",
				"/api/transaction/getResponse/*"
			 ).permitAll()
	 .anyRequest().authenticated()
	 .and().exceptionHandling().and().sessionManagement()
	 .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	  http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//	  http.cors();
//	  http.csrf().disable()
//	  .authorizeRequests()
//	  .antMatchers("/**")
//	  .permitAll()
//	  .anyRequest()
//	  .authenticated()
//	  .and()
//	  .exceptionHandling()
//	  .and()
//	  .sessionManagement()
//	  .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//	  	http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//		http.csrf().disable()
//		.authorizeRequests()
//		.antMatchers("/authenticate",
//				"/api/partenaires/list*", 
//				"/api/evenement/get/*",
//				"/api/transaction/saveTransaction/*").permitAll()
//		.anyRequest().authenticated().and().exceptionHandling().and().sessionManagement()
//		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	}
}

