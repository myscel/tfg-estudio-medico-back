package com.example.tfgestudiomedico2019.model.rest.investigation;

import io.swagger.annotations.ApiModelProperty;

/**
 * Investigation dto with all the information to edit an investigation.
 *
 */
public class InvestigationToEditDto {

	@ApiModelProperty(value = "The subject numbers from health card", example = "12345678", dataType = "java.lang.Integer")
	private String subjectIdentificationNumber;
	@ApiModelProperty(value = "The investigation number", example = "1", dataType = "java.lang.Integer")
	private Integer numberInvestigation;
	@ApiModelProperty(value = "The investigation details number", example = "8", dataType = "java.lang.Integer")
	private Integer investigationDetailsId;
	
	public InvestigationToEditDto(String subjectIdentificationNumber, Integer numberInvestigation, Integer investigationDetailsId) {
		super();
		this.subjectIdentificationNumber = subjectIdentificationNumber;
		this.numberInvestigation = numberInvestigation;
		this.investigationDetailsId = investigationDetailsId;
	}
	
	public InvestigationToEditDto() {
	}
	public String getSubjectIdentificationNumber() {
		return subjectIdentificationNumber;
	}
	public void setSubjectIdentificationNumber(String subjectIdentificationNumber) {
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
