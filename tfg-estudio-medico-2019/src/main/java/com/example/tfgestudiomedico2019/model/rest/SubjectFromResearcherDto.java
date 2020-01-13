package com.example.tfgestudiomedico2019.model.rest;

import io.swagger.annotations.ApiModelProperty;

public class SubjectFromResearcherDto {
	@ApiModelProperty(value="The id of the subject", example = "2")
	private Integer id;
	@ApiModelProperty(value="The identification number of the subject", example = "12345678")
	private Integer identificationNumber;
	@ApiModelProperty(value="Flag to check if the first investigation is done", example="true" )
	private Boolean firstInvestigationCompleted;
	@ApiModelProperty(value="Flag to check if the second investigation is done", example="false" )
	private Boolean secondInvestigationCompleted;
	
	
	public Integer getIdentificationNumber() {
		return identificationNumber;
	}
	public void setIdentificationNumber(Integer identificationNumber) {
		this.identificationNumber = identificationNumber;
	}
	public Boolean getFirstInvestigationCompleted() {
		return firstInvestigationCompleted;
	}
	public void setFirstInvestigationCompleted(Boolean firstInvestigationCompleted) {
		this.firstInvestigationCompleted = firstInvestigationCompleted;
	}
	public Boolean getSecondInvestigationCompleted() {
		return secondInvestigationCompleted;
	}
	public void setSecondInvestigationCompleted(Boolean secondInvestigationCompleted) {
		this.secondInvestigationCompleted = secondInvestigationCompleted;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "SubjectFromResearcherDto [id=" + id + ", identificationNumber=" + identificationNumber
				+ ", firstInvestigationCompleted=" + firstInvestigationCompleted + ", secondInvestigationCompleted="
				+ secondInvestigationCompleted + "]";
	}	
}
