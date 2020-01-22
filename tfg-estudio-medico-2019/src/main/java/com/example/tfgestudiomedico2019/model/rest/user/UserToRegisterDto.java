package com.example.tfgestudiomedico2019.model.rest.user;

import io.swagger.annotations.ApiModelProperty;

/**
 *  User dto with information to register a user.
 *
 */
public class UserToRegisterDto {
	@ApiModelProperty(value = "The username of the user", example = "12345678A", dataType = "java.lang.String")
	private String username;
	@ApiModelProperty(value = "The name of the user", example = "Sergio", dataType = "java.lang.String")
	private String name;
	@ApiModelProperty(value = "The gender of the user", example = "hombre", allowableValues="hombre, mujer", dataType = "java.lang.String")
	private String gender;
	@ApiModelProperty(value = "The surname of the user", example = "Pacheco Fernández", dataType = "java.lang.String")
	private String surname;
	@ApiModelProperty(value = "The password of the user", example = "123456", dataType = "java.lang.String")
	private String password;
	@ApiModelProperty(value = "The role of the user", example = "ADMIN", allowableValues="ADMIN, RESEARCHER", dataType = "java.lang.String")
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


	@Override
	public String toString() {
		return "UserToRegisterDto [username=" + username + ", name=" + name + ", gender=" + gender + ", surname="
				+ surname + ", password=" + password + ", role=" + role + "]";
	}
}
