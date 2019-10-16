package com.example.tfgestudiomedico2019.model.rest;

public class SubjectFromResearcherDto {
	private Integer identificationNumber;
	private Boolean firstInvestigationCompleted;
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
	@Override
	public String toString() {
		return "SubjectFromResearcherDto [identificationNumber=" + identificationNumber
				+ ", firstInvestigationCompleted=" + firstInvestigationCompleted + ", secondInvestigationCompleted="
				+ secondInvestigationCompleted + "]";
	}
	
	
	

	
}
