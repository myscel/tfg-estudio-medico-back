package com.example.tfgestudiomedico2019.model.rest;

import com.example.tfgestudiomedico2019.model.entity.UserEntity;

import io.swagger.annotations.ApiModelProperty;

public class SubjectInfoDto {
	@ApiModelProperty(value = "The identification number of the subject", example = "12345678")
	private Integer identificationNumber;
	@ApiModelProperty(value = "The dni of the researcher who investigates the subject", example = "12345678A")
	private String usernameResearcher;
	
	
	public SubjectInfoDto(Integer identificationNumber, String usernameResearcher) {
		super();
		this.identificationNumber = identificationNumber;
		this.usernameResearcher = usernameResearcher;
	}
	public SubjectInfoDto() {
		// TODO Auto-generated constructor stub
	}
	public Integer getIdentificationNumber() {
		return identificationNumber;
	}
	public void setIdentificationNumber(Integer identificationNumber) {
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
		return "SubjectInfoDto [identificationNumber=" + identificationNumber + ", usernameResearcher="
				+ usernameResearcher + "]";
	}	
}
