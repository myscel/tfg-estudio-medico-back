package com.example.tfgestudiomedico2019.business.subject;

public interface SubjectBusiness {
	public Boolean deleteSubjectByIdentificationNumber(Integer identificationNumber);
	
	public Integer getNumberInvestigationsCompletedFromSubject(Integer identificationNumber);
}
