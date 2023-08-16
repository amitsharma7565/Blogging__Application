package com.amit.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amit.blog.entites.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
