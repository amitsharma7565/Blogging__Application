package com.amit.blog.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amit.blog.config.AppConstants;
import com.amit.blog.entites.Role;
import com.amit.blog.entites.User;
import com.amit.blog.exceptions.ResourceNotFoundException;
import com.amit.blog.payloads.UserDto;
import com.amit.blog.repositories.RoleRepo;
import com.amit.blog.repositories.UserRepo;
import com.amit.blog.services.UserService;
@Service
public class UserServiceImpl implements UserService{
@Autowired
	private UserRepo userRepo;
@Autowired
private ModelMapper modelMapper;
@Autowired
private PasswordEncoder passwordEncoder;
@Autowired
private RoleRepo roleRepo;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User savedUser= userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId){
			User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
			user.setName(userDto.getName());
			user.setEmail(userDto.getEmail());
			user.setPassword(userDto.getPassword());
			user.setAbout(userDto.getAbout());
			
			User updatedUser= this.userRepo.save(user);
			UserDto userDto1= this.userToDto(updatedUser);
			return userDto1;

	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
List<User> users=this.userRepo.findAll();
List<UserDto> usersDto= users.stream().map(user->this.userToDto(user)).collect(Collectors.toList()) ;
		return usersDto;
	}

	@Override
	public void deleteUser(Integer id) {
		User user=this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
		this.userRepo.delete(user);
	}
	
	
	public User dtoToUser(UserDto userDto) {
		User user= this.modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		return user;
		
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
		return userDto;
		
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user= this.modelMapper.map(userDto, User.class);
		//encode the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		// set the roles normally i set the role for new user only normal user
		 Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		 user.getRoles().add(role);
		 User newUser= this.userRepo.save(user);
		return this.modelMapper.map(newUser, UserDto.class);
	}

}