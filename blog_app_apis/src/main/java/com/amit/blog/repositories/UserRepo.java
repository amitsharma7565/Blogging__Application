package com.amit.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amit.blog.entites.User;

public interface UserRepo extends JpaRepository<User,Integer>{

}