package com.example.tfgestudiomedico2019.model.rest;

import io.swagger.annotations.ApiModelProperty;

public class SubjectToRegisterDto {
	@ApiModelProperty(value = "The subject identification number", example = "11111111")
	private String identificationNumber;
	
	@ApiModelProperty(value = "The username researcher", example = "12345678A")
	private String usernameResearcher;
	
	
	public SubjectToRegisterDto(String identificationNumber, String usernameResearcher) {
		super();
		this.identificationNumber = identificationNumber;
		this.usernameResearcher = usernameResearcher;
	}
	
	public String getIdentificationNumber() {
		return identificationNumber;
	}
	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}
	public String getUsernameResearcher() {
		return usernameResearcher;
	}
	public void setUsernameResearcher(String usernameResearcher) {
		this.usernameResearcher = usernameResearcher;
	}
	
	
	@Override
	public String toString() {
		return "SubjectToRegisterDto [identificationNumber=" + identificationNumber + ", usernameResearcher="
				+ usernameResearcher + "]";
	}
	
	
	
}
