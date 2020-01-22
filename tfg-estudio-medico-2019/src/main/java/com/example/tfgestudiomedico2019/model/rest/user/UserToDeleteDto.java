package com.example.tfgestudiomedico2019.model.rest.user;

import io.swagger.annotations.ApiModelProperty;

public class UserToDeleteDto {
	@ApiModelProperty(value = "The username of the user", example = "12345678A")
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "UserToDeleteDto [username=" + username + "]";
	}
}
