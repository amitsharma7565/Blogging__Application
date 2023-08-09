package com.amit.blog.services;

import java.util.List;

import com.amit.blog.entites.Post;
import com.amit.blog.payloads.PostDto;

public interface PostService {
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	Post updatePost(PostDto postDto, Integer postId);
	void deletePost(Integer postId);
	List<Post> getAllPost();
	Post getPostById(Integer postId);
	List<PostDto> getPostByCategory(Integer categoryId);
	List<PostDto> getPostByUser(Integer UserId);
	List<Post> searchPosts(String keyword);
}
