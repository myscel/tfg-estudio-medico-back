package com.example.tfgestudiomedico2019.model.rest;


public class UserDto {
	
	private String username;
	private String name;
	
	
	public UserDto(String username, String name) {
		super();
		this.username = username;
		this.name = name;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
