
package com.beIt.filter;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.beIt.service.UserDetailService;
import com.beIt.util.JwtUtil;


@Component
public class JwtFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtUtil jwtUtill;
	
	@Autowired
	private UserDetailService service;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin","*");
		response.addHeader("Access-Control-Allow-Headers", "Origin,Accept,X-Requested-With, Content-type,Acess-Control-Requested-Method,Access-Control-Request-Headers,authorization");
		response.addHeader("Access-Control-Expose-Headers","Access-Control-Allow-Origin,Access-Control-Allow-Credentials,authorization");
		response.addHeader("Access-Control-Allow-Methods","GET,POST,PUT,DELETE,PATCH");
		
		if(request.getMethod().equals("OPTIONS")) {
			response.setStatus(HttpServletResponse.SC_OK);
		}
		
		//Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ4YWRtaW4iLCJleHAiOjE1OTUxODMyMTcsImlhdCI6MTU5NTE0NzIxN30.S9w8LROFAKYCWU2nsHu07FcWGfI5vwesC4MvsqzGDyo
		String autherizationHeader = request.getHeader("Authorization");
		//System.out.println("Authorization : "+ request.getHeader("authorization"));
		
		String token = null;
		String username=null;
		if(autherizationHeader !=null && autherizationHeader.startsWith("Bearer"))
		{
			token = autherizationHeader.substring(7);
			username = jwtUtill.extractUsername(token);
		}
		
		if(username !=null && SecurityContextHolder.getContext().getAuthentication() == null)
		{
			UserDetails userDetails = service.loadUserByUsername(username);
			
			if (jwtUtill.validateToken(token, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}
}
