package com.amit.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.amit.blog.config.AppConstants;
import com.amit.blog.entites.Role;
import com.amit.blog.repositories.RoleRepo;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import org.modelmapper.ModelMapper;
@SpringBootApplication
@EnableSwagger2

public class BlogAppApisApplication implements CommandLineRunner{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}
@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
@Override
public void run(String... args) throws Exception {
	System.out.println(this.passwordEncoder.encode("a"));
	// when the application start this two role save in the database if not save
	try {
		Role role = new Role();
		role.setId(AppConstants.ADMIN_USER);
		role.setName("ADMIN_USER");
		Role role1 = new Role();
		role1.setId(AppConstants.NORMAL_USER);
		role1.setName("NORMAL_USER");
		
		// Save this two role in database
		List<Role> roles = List.of(role,role1);
		List<Role> result = this.roleRepo.saveAll(roles);
		
		result.forEach(r->{
			System.out.println(r.getName());
		});
	}
	catch(Exception e) {
		System.out.println(e);
	}
}
}
