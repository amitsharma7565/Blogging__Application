package com.amit.blog.controllers;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amit.blog.payloads.ApiResponse;
import com.amit.blog.payloads.UserDto;
import com.amit.blog.services.UserService;



@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
private UserService userService;
	@PostMapping("/createUser")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto ){
		UserDto createUserDto= this.userService.createUser(userDto);
		return  new ResponseEntity<>(createUserDto, HttpStatus.CREATED)  ;
	}
//	update the user
	@PutMapping("/update/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId){
		UserDto updateUser= this.userService.updateUser(userDto, userId);
		return new ResponseEntity<>(updateUser,HttpStatus.OK);
	}
	
	
	//delete the user
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
		 this.userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User delete sucessFully",true),HttpStatus.OK);
	}
	// getUser
	@GetMapping("/getUser")
	public ResponseEntity<List<UserDto>> getUser(){
		List<UserDto> getAll= this.userService.getAllUser();
		return new ResponseEntity<>(getAll,HttpStatus.OK);
	}
	@GetMapping("/getUser/{UserId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer UserId ){
		UserDto getSingleUser= this.userService.getUserById(UserId);
		return new ResponseEntity<>(getSingleUser,HttpStatus.OK);
	}
	
}