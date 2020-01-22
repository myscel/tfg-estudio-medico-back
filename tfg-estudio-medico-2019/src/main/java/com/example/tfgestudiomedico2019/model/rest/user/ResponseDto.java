package com.example.tfgestudiomedico2019.model.rest.user;

import io.swagger.annotations.ApiModelProperty;

/**
 * Message information in HTTP responses.
 *
 */
public class ResponseDto {

	public ResponseDto(String description) {
	this.description = description;
	}

	@ApiModelProperty(value="The description of the response", example= "User updated successfull", dataType = "java.lang.String")
	private String description;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ResponseDto [description=" + description + "]";
	}
}
