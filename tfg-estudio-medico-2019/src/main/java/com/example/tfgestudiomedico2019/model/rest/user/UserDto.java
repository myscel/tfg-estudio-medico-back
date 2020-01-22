package com.example.tfgestudiomedico2019.model.rest.user;

import io.swagger.annotations.ApiModelProperty;

/**
 * User dto general.
 *
 */
public class UserDto {

	@ApiModelProperty(value = "The username of the user", example = "12345678A", dataType = "java.lang.String")
	private String username;
	@ApiModelProperty(value = "The name of the user", example = "Sergio", dataType = "java.lang.String")
	private String name;
	@ApiModelProperty(value = "The gender of the user", example = "hombre", allowableValues="hombre, mujer", dataType = "java.lang.String")
	private String gender;
	@ApiModelProperty(value = "The id of the user", example = "23", dataType = "java.lang.Integer")
	private Integer id;
	@ApiModelProperty(value = "The surname of the user", example = "Pacheco Fernández", dataType = "java.lang.String")
	private String surname;
	
	
	public UserDto(String username, String name, String surname, String gender, Integer id) {
		super();
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.id = id;
	}
	
	public UserDto() {
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	@Override
	public String toString() {
		return "UserDto [username=" + username + ", name=" + name + ", gender=" + gender + ", id=" + id + ", surname="
				+ surname + "]";
	}	
}
