package com.example.tfgestudiomedico2019.model.rest.user;

import io.swagger.annotations.ApiModelProperty;

public class ResponseDto {

	public ResponseDto(String description) {
	this.description = description;
	}

	@ApiModelProperty(value="The description of the response", example= "Message information")
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
