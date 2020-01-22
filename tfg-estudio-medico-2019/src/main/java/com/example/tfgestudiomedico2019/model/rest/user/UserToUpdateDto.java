package com.example.tfgestudiomedico2019.model.rest.user;

import io.swagger.annotations.ApiModelProperty;

public class UserToUpdateDto {
	@ApiModelProperty(value = "The name of the user", example = "SERGIO")
	private String name;
	@ApiModelProperty(value = "The surname of the user", example = "PACHECO FERNÁNDEZ")
	private String surname;
	@ApiModelProperty(value = "The password of the user", example = "123ABC")
	private String password;
	@ApiModelProperty(value = "The id of the user", example = "23")
	private String id;
	
	
	
	public UserToUpdateDto() {
		super();
	}

	public UserToUpdateDto(String name, String surname, String password, String id) {
		super();
		this.name = name;
		this.surname = surname;
		this.password = password;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
