package com.amit.blog.entites;




import java.util.*;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import javax.persistence.JoinColumn;
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private Integer id;
private String name;
private String email;
private String password;
private String about;

@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
private List<Post> posts = new ArrayList<>();

@ManyToMany
@JoinTable(
    name = "user_role",
    joinColumns = { @JoinColumn(name = "user", referencedColumnName = "id") },
    inverseJoinColumns = { @JoinColumn(name = "role", referencedColumnName = "id") }
)
private Set<Role> roles = new HashSet<>();





public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
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

public List<Post> getPosts() {
	return posts;
}

public void setPosts(List<Post> posts) {
	this.posts = posts;
}

public User(Integer id, String name, String email, String password, String about, List<Post> posts) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.password = password;
	this.about = about;
	this.posts = posts;
}

public User() {
	super();
	// TODO Auto-generated constructor stub
}

}