package com.example.tfgestudiomedico2019.model.rest;

import io.swagger.annotations.ApiModelProperty;

public class UserToLoginDto {
	
	@ApiModelProperty(value = "The username of the user", example = "12345678A")
	private String username;
	@ApiModelProperty(value = "The password of the user")
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserToLoginDto [username=" + username + ", password=" + password + "]";
	}
}
