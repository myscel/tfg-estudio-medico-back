package com.example.tfgestudiomedico2019.model.rest;

import io.swagger.annotations.ApiModelProperty;

public class InvestigationToEditDto {

	@ApiModelProperty(value = "The subject numbers from health card", example = "12345678")
	private Integer subjectIdentificationNumber;
	@ApiModelProperty(value = "The appointment number", example = "1")
	private Integer numberInvestigation;
	@ApiModelProperty(value = "The investigation details number", example = "8")
	private Integer investigationDetailsId;
	
	public InvestigationToEditDto(Integer subjectIdentificationNumber, Integer numberInvestigation, Integer investigationDetailsId) {
		super();
		this.subjectIdentificationNumber = subjectIdentificationNumber;
		this.numberInvestigation = numberInvestigation;
		this.investigationDetailsId = investigationDetailsId;
	}
	
	public InvestigationToEditDto() {
	}
	
	public Integer getSubjectIdentificationNumber() {
		return subjectIdentificationNumber;
	}

	public void setSubjectIdentificationNumber(Integer subjectIdentificationNumber) {
		this.subjectIdentificationNumber = subjectIdentificationNumber;
	}

	public Integer getNumberInvestigation() {
		return numberInvestigation;
	}

	public void setNumberInvestigation(Integer numberInvestigation) {
		this.numberInvestigation = numberInvestigation;
	}

	public Integer getInvestigationDetailsId() {
		return investigationDetailsId;
	}

	public void setInvestigationDetailsId(Integer investigationDetailsId) {
		this.investigationDetailsId = investigationDetailsId;
	}
	public String toString() {
		return "InvestigationToEditDto [subjectIdentificationNumber=" + subjectIdentificationNumber + ", numberInvestigation=" + numberInvestigation + ""
				+ ", investigationDetailsId=" + investigationDetailsId + "]";
	}
}
