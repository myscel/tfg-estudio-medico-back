package com.example.tfgestudiomedico2019.model.rest;

import lombok.Data;

@Data
public class ResponseDto {

	public ResponseDto(String description) {
	this.description = description;
	}

	private String description;

}
