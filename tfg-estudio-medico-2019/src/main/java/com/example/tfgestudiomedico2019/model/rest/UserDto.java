package com.example.tfgestudiomedico2019.model.rest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class UserDto {
	
	private Integer id;
	private String dni;
	private String password;

}
