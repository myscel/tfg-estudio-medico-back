package com.example.tfgestudiomedico2019.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "user")
public class UserEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	private String username;
	private String gender;
	private String password;
	private String role;
	private String surname;
	
	@OneToMany(
	        mappedBy = "researcher",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	)
	private List<SubjectEntity> subjects = new ArrayList<>();
	
	
	public List<SubjectEntity> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<SubjectEntity> subjects) {
		this.subjects = subjects;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", name=" + name + ", username=" + username + ", gender=" + gender
				+ ", password=" + password + ", role=" + role + ", surname=" + surname + ", subjects=" + subjects + "]";
	}
}
