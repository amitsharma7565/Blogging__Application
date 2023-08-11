package com.amit.blog.services;

import java.util.List;

import com.amit.blog.entites.Post;
import com.amit.blog.payloads.PostDto;
import com.amit.blog.payloads.PostResponse;

public interface PostService {
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	PostDto updatePost(PostDto postDto, Integer postId);
	void deletePost(Integer postId);
	PostResponse getAllPost(Integer pageNumber, Integer pageSize);
	PostDto getPostById(Integer postId);
	List<PostDto> getPostByCategory(Integer categoryId);
	List<PostDto> getPostByUser(Integer UserId);
	List<Post> searchPosts(String keyword);
}
