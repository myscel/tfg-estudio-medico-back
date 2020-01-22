package com.example.tfgestudiomedico2019.model.rest.user;

import io.swagger.annotations.ApiModelProperty;

/**
 * User dto to return the user logged information.
 *
 */
public class UserLoggedDto {
	@ApiModelProperty(value = "The id of the user", example = "23", dataType = "java.lang.Integer")
	private Integer id;
	@ApiModelProperty(value = "The name of the user", example = "Juan", dataType = "java.lang.String")
	private String name;
	@ApiModelProperty(value = "The surname of the user", example = "García Sánchez", dataType = "java.lang.String")
	private String surname;
	@ApiModelProperty(value = "The username of the user", example = "12345678A", dataType = "java.lang.String")
	private String username;
	@ApiModelProperty(value = "The gender of the user", example = "hombre", allowableValues="hombre, mujer", dataType = "java.lang.String")
	private String gender;
	@ApiModelProperty(value = "The role of the user", example = "ADMIN", allowableValues="ADMIN, RESEARCHER", dataType = "java.lang.String")
	private String role;
	
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "UserLoggedDto [id=" + id + ", name=" + name + ", surname=" + surname + ", username=" + username
				+ ", gender=" + gender + ", role=" + role + "]";
	}
}
