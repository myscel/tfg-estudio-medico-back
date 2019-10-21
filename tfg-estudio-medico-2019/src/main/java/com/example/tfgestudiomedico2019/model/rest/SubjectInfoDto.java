package com.example.tfgestudiomedico2019.model.rest;

public class SubjectInfoDto {
	private Integer identificationNumber;
	private String usernameResearcher;
	
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
