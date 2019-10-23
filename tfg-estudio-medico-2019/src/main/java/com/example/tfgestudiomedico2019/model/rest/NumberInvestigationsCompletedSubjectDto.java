package com.example.tfgestudiomedico2019.model.rest;

public class NumberInvestigationsCompletedSubjectDto {
	
	private Integer numberInvestigationsCompleted;
	
	public NumberInvestigationsCompletedSubjectDto() {
		this.numberInvestigationsCompleted = 0;
	}
	
	public NumberInvestigationsCompletedSubjectDto(Integer numberInvestigationsCompleted) {
		super();
		this.numberInvestigationsCompleted = numberInvestigationsCompleted;
	}


	public Integer getNumberInvestigationsCompleted() {
		return numberInvestigationsCompleted;
	}

	public void setNumberInvestigationsCompleted(Integer numberInvestigationsCompleted) {
		this.numberInvestigationsCompleted = numberInvestigationsCompleted;
	}

	@Override
	public String toString() {
		return "NumberInvestigationsCompletedSubjectDto [numberInvestigationsCompleted=" + numberInvestigationsCompleted
				+ "]";
	}
	
}
