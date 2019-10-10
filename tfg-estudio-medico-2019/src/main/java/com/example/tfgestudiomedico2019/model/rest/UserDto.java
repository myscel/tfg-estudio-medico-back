package com.example.tfgestudiomedico2019.model.rest;


public class UserDto {
	
	private String username;
	private String name;
	private String gender;
	
	
	public UserDto(String username, String name, String gender) {
		super();
		this.username = username;
		this.name = name;
		this.gender = gender;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
}
