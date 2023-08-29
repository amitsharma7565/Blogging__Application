package com.amit.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.amit.blog.entites.Role;

public class UserDto {
	private Integer id;
	@NotEmpty
	@Size(min=4, message="username should be min of 4 char")
	private String name;
	@Email(message="Email address not valid")
	private String email;
	@NotEmpty
	
	private String password;
	@NotEmpty
	@Size(min=3,max=10, message="password must be min of 3 chars and max of 10 chars")
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Set<RoleDto> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", about="
				+ about + ", roles=" + roles + "]";
	}
	public UserDto(Integer id, @NotEmpty @Size(min = 4, message = "username should be min of 4 char") String name,
			@Email(message = "Email address not valid") String email, @NotEmpty String password,
			@NotEmpty @Size(min = 3, max = 10, message = "password must be min of 3 chars and max of 10 chars") String about,
			Set<RoleDto> roles) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
		this.roles = roles;
	}
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}