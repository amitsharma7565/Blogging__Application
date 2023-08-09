package com.amit.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amit.blog.entites.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
