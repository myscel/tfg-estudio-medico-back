package com.example.tfgestudiomedico2019.model.rest.user;

import io.swagger.annotations.ApiModelProperty;

/**
 * User dto with information to login a user.
 *
 */
public class UserToLoginDto {
	
	@ApiModelProperty(value = "The username of the user", example = "12345678A", dataType = "java.lang.String")
	private String username;
	@ApiModelProperty(value = "The password of the user", example = "123456", dataType = "java.lang.String")
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
