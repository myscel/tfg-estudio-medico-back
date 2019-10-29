package com.example.tfgestudiomedico2019.model.rest;


public class UserDto {
	
	private String username;
	private String name;
	private String gender;
	private Integer id;
	private String surname;
	
	
	public UserDto(String username, String name, String surname, String gender, Integer id) {
		super();
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.id = id;
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
