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
	
	
	public String toString() {
		return "InvestigationToEditDto [subjectIdentificationNumber=" + subjectIdentificationNumber + ", numberInvestigation=" + numberInvestigation + ""
				+ ", investigationDetailsId=" + investigationDetailsId + "]";
	}
}
