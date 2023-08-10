package com.amit.blog.services;

import java.util.List;

import com.amit.blog.entites.Post;
import com.amit.blog.payloads.PostDto;

public interface PostService {
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	PostDto updatePost(PostDto postDto, Integer postId);
	void deletePost(Integer postId);
	List<PostDto> getAllPost();
	PostDto getPostById(Integer postId);
	List<PostDto> getPostByCategory(Integer categoryId);
	List<PostDto> getPostByUser(Integer UserId);
	List<Post> searchPosts(String keyword);
}
