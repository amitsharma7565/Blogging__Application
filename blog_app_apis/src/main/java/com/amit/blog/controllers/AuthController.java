package com.amit.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amit.blog.payloads.JwtAuthRequest;
import com.amit.blog.payloads.JwtAuthResponse;
import com.amit.blog.security.JwtTokenHelper;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(
			@RequestBody JwtAuthRequest request
			) throws Exception{
		this.authenticate(request.getUsername(),request.getPassword());
		UserDetails userDetails= this.userDetailsService.loadUserByUsername(request.getUsername());
		String token= this.jwtTokenHelper.generateToken(userDetails);
		
		JwtAuthResponse response =new JwtAuthResponse();
		response.setToken(token);
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception{
		UsernamePasswordAuthenticationToken autheticationToken= new UsernamePasswordAuthenticationToken(username, password);
		try {
			this.authenticationManager.authenticate(autheticationToken);
		}
		catch(Exception e) {
			System.out.println("invalid details");
			throw new Exception("invalid Details try again with correct username or password");
		}
				
		
		
		
		
	}
	
}
