package com.example.tfgestudiomedico2019.model.entity;

public class JwtUser {
	
	private String username;
	private Long id;
	private String role;

	public void setUsername(String username) {
		this.username = username;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public Long getId() {
		return id;
	}

	public String getRole() {
		return role;
	}
	
	

}
