package com.amit.blog.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetails;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	// get token 
		String requestToken= request.getHeader("Authorization");
		// format of token 
		// Bearer 22342dsfs;
		
		System.out.println("requestToken is "+requestToken);
		// in this token fetch the username and token
		
		String username=null;
		String token=null;
		
		if(request!=null&&requestToken.startsWith("Bearer")) {
			// get token and remove the Bearer
			token=	requestToken.substring(7);
			
			try {
				this.jwtTokenHelper.getUsernameFromToken(token);
			}
			catch(Exception e) {
				System.out.println(e);
			}
			
			
		}
		else {
			System.out.println("jwt token does not begin with Bearer");
		}
		// once we get the token now  step to validate the token 
		//2 validate the token
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null ) {
			
			UserDetails userDetails= this.userDetails.loadUserByUsername(username);
			if(this.jwtTokenHelper.validateToken(token, userDetails)) {
				// do authecation with the help of SecurityContextHolder
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				// setDetails
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			}
			else {
				System.out.println("Invalid jwt Token");
			}
		}
		else {
			System.out.println("username is null or context is not null");
		}
	}

}
