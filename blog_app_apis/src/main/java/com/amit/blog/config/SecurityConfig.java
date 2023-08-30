package com.amit.blog.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.amit.blog.security.CustomUserDetailService;
import com.amit.blog.security.JwtAuthenticationEntryPoint;
import com.amit.blog.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableWebMvc 
public class SecurityConfig  {
	//extends WebSecurityConfiguration
	//define array and in this array store all url that i want to do public
	public static final String[] PUBLIC_URLS= {
			"/api/v1/auth/**",
	};
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter; 
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http
      .csrf()
      .disable()
      .authorizeHttpRequests()
//      login api public if i want to public any other then pass here and then that url is public 
      // and here this two ** means it allow that 2 methods that is present in AuthController
      .requestMatchers(PUBLIC_URLS).permitAll()
      // this 46 line is for all person access the get method without login access 
      .requestMatchers(HttpMethod.GET).permitAll()
      .anyRequest()
      .authenticated()
      .and()
      .exceptionHandling()
      .authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
      .and()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      
      
      http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
      
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
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
    
}

