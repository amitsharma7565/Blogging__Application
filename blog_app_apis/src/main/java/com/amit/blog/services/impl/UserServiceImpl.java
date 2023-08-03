package com.amit.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amit.blog.entites.User;
import com.amit.blog.exceptions.ResourceNotFoundException;
import com.amit.blog.payloads.UserDto;
import com.amit.blog.repositories.UserRepo;
import com.amit.blog.services.UserService;
@Service
public class UserServiceImpl implements UserService{
@Autowired
	private UserRepo userRepo;
@Autowired
private ModelMapper modelMapper;
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

}