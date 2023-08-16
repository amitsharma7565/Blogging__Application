package com.amit.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amit.blog.entites.Category;
import com.amit.blog.entites.Comment;
import com.amit.blog.entites.Post;
import com.amit.blog.exceptions.ResourceNotFoundException;
import com.amit.blog.payloads.CommentDto;
import com.amit.blog.repositories.CommentRepo;
import com.amit.blog.repositories.PostRepo;
import com.amit.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post= this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","post id",postId));
		 Comment comment= this.modelMapper.map(commentDto,Comment.class);
		 comment.setPost(post);
		 Comment savedComment= this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment com=commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment","comment id",commentId));
		this.commentRepo.delete(com);
	}
	
	

}
