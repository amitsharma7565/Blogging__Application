package com.amit.blog.services.impl;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amit.blog.entites.Category;
import com.amit.blog.entites.Post;
import com.amit.blog.entites.User;
import com.amit.blog.exceptions.ResourceNotFoundException;
import com.amit.blog.payloads.PostDto;
import com.amit.blog.repositories.CategoryRepo;
import com.amit.blog.repositories.PostRepo;
import com.amit.blog.repositories.UserRepo;
import com.amit.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user= userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","UserId",userId));
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CateoryId",categoryId));
		Post post=this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date(0, 0, 0));
		post.setCategory(category);
		post.setUser(user);
		Post newPost= this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public Post updatePost(PostDto postDto, Integer postId) {

		return null;
	}

	@Override
	public void deletePost(Integer postId) {

		
	}

	@Override
	public List<Post> getAllPost() {

		return null;
	}

	@Override
	public Post getPostById(Integer postId) {

		return null;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
		List<Post> posts= this.postRepo.findByCategory(cat);
		List<PostDto> collect= posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return collect;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		List<Post> posts=this.postRepo.findByUser(user);
//		all the post we need to convert in postDto
		List<PostDto> collect=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return collect;
	}

	@Override
	public List<Post> searchPosts(String keyword) {

		return null;
	}

}
