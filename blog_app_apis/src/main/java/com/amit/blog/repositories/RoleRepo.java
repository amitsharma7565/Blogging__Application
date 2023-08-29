package com.amit.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amit.blog.entites.Role;

public interface RoleRepo extends JpaRepository<Role,Integer>{

}
