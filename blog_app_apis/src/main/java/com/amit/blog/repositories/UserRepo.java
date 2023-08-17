package com.amit.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amit.blog.entites.User;

public interface UserRepo extends JpaRepository<User,Integer>{

	Optional<User> findByEmail(String email);
}