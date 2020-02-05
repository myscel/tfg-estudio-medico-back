package com.example.tfgestudiomedico2019.model.rest.subject;

import io.swagger.annotations.ApiModelProperty;

/**
 * Subject dto that contains the main information about him/her and the researcher.
 *
 */
public class SubjectInfoDto {
	@ApiModelProperty(value = "The identification number of the subject", example = "12345678", dataType = "java.lang.Integer")
	private String identificationNumber;
	@ApiModelProperty(value = "The dni of the researcher who investigates the subject", example = "12345678A", dataType = "java.lang.String")
	private String usernameResearcher;
	
	
	public SubjectInfoDto(String identificationNumber, String usernameResearcher) {
		super();
		this.identificationNumber = identificationNumber;
		this.usernameResearcher = usernameResearcher;
	}
	public SubjectInfoDto() {
		// TODO Auto-generated constructor stub
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
		return "SubjectInfoDto [identificationNumber=" + identificationNumber + ", usernameResearcher="
				+ usernameResearcher + "]";
	}	
}
