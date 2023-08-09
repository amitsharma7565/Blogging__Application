package com.amit.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amit.blog.entites.Post;
import com.amit.blog.payloads.PostDto;
import com.amit.blog.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
	@Autowired
	private  PostService postService;
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId){
		
		PostDto createPost=this.postService.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	//get by User an return that user all posts
	 @GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
		 List<PostDto> posts= this.postService.getPostByUser(userId);
		 return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		 
	 }
	 
	 @GetMapping("/category/{categoryId}/posts")
	 public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
		List<PostDto> posts= this.postService.getPostByCategory(categoryId);
		 return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	 }
	 
	 
	 
	
}
