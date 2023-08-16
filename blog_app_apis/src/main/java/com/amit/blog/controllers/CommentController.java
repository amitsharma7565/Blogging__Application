package com.amit.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amit.blog.entites.Comment;
import com.amit.blog.payloads.ApiResponse;
import com.amit.blog.payloads.CommentDto;
import com.amit.blog.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable Integer postId){
		CommentDto commentDto= this.commentService.createComment(comment, postId);
		return new ResponseEntity<CommentDto>(commentDto,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		 this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("delete succesfully",true),HttpStatus.OK);
	}
}
