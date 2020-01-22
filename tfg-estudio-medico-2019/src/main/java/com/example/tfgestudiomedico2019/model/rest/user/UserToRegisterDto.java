package com.example.tfgestudiomedico2019.model.rest.user;

import io.swagger.annotations.ApiModelProperty;

public class UserToRegisterDto {
	@ApiModelProperty(value = "The username of the user", example = "12345678A")
	private String username;
	@ApiModelProperty(value = "The name of the user", example = "SERGIO")
	private String name;
	@ApiModelProperty(value = "The gender of the user", example= "RESEARCHER")
	private String gender;
	@ApiModelProperty(value = "The surname of the user", example = "PACHECO FERNÁNDEZ")
	private String surname;
	@ApiModelProperty(value = "The password of the user")
	private String password;
	@ApiModelProperty(value = "The role of the user", example = "RESEARCHER")
	private String role;
	
	

	public UserToRegisterDto() {}
	
	
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
