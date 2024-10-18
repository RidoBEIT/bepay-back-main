package com.beIt.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;


public class AuditorAwareImpl implements AuditorAware<String> {	
	@Override
    public Optional<String> getCurrentAuditor() {
        //return "Naresh";
        // Can use Spring Security to return currently logged in user
        // return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()
	
		String loggedUser = "SYSTEM";
		//User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null)
		{
			loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
			//loggedUser = user.getLogin();
		}
		return Optional.ofNullable(loggedUser);
    }
}
