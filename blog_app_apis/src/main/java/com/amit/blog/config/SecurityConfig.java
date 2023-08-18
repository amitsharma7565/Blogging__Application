package com.amit.blog.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.amit.blog.security.CustomUserDetailService;


@Configuration
public class SecurityConfig {
	
	@Autowired
private CustomUserDetailService customUserDetailService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http.csrf().disable().authorizeHttpRequests().anyRequest().authenticated().and().httpBasic();
       return http.build();
    }

    protected void config(AuthenticationManagerBuilder auth) throws Exception{
    	auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder()); 
    }
// to BCrypt the Password store the password in database in the form of BCrypt
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
}

