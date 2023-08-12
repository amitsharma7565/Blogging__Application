package com.amit.blog.services.impl;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.amit.blog.entites.Category;
import com.amit.blog.entites.Post;
import com.amit.blog.entites.User;
import com.amit.blog.exceptions.ResourceNotFoundException;
import com.amit.blog.payloads.PostDto;
import com.amit.blog.payloads.PostResponse;
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
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post= this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","post id",postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		Post updatePost= this.postRepo.save(post);
		return this.modelMapper.map(updatePost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post= this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","post id",postId));
		this.postRepo.delete(post);
		
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy,String sortDir) {
		Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable p=PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost= this.postRepo.findAll(p);
		List<Post> allPosts= pagePost.getContent();
		List<PostDto> collectionOfPost= allPosts.stream().map((post)-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse= new PostResponse();
		postResponse.setContent(collectionOfPost);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","postId",postId));
		PostDto postDto= this.modelMapper.map(post, PostDto.class);
		return postDto;
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
	public List<PostDto> searchPosts(String keyword) {
 	List<Post> posts=this.postRepo.findByTitleContaining("%"+keyword+"%");
 	List<PostDto> postDtos= posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}
