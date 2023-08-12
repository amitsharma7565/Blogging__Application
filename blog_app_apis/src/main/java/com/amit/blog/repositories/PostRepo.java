package com.amit.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amit.blog.entites.Category;
import com.amit.blog.entites.Post;
import com.amit.blog.entites.User;

public interface PostRepo extends JpaRepository<Post,Integer> {

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	@Query("select p from Post p where p.title like :key")
	List<Post> findByTitleContaining( @Param("key")String title);
}
