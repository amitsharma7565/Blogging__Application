package com.amit.blog.payloads;

public class JwtAuthResponse {
private String token;

public String getToken() {
	return token;
}

public void setToken(String token) {
	this.token = token;
}

public JwtAuthResponse(String token) {
	super();
	this.token = token;
}

public JwtAuthResponse() {
	// TODO Auto-generated constructor stub
}

@Override
public String toString() {
	return "JwtAuthResponse [token=" + token + "]";
}

}
