package com.example.tfgestudiomedico2019.business.subject;

import java.util.List;

import com.example.tfgestudiomedico2019.model.entity.InvestigationEntityDetails;
import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;

/**
 * Subject business.
 */
public interface SubjectBusiness {
	public SubjectEntity saveSubject(SubjectEntity subject);
	public Boolean deleteSubjectByIdentificationNumber(String identificationNumber);
	public Integer getNumberInvestigationsCompletedFromSubject(String identificationNumber);
	public SubjectEntity getSubjectFromIdentificationNumber(String identificationNumber);
	public List<SubjectEntity> getSubjectsFromDNIResearcher(String username);
	public List<SubjectEntity> getAllSubjects();
	public InvestigationEntityDetails getInvestigationDetailsFromId(Integer id);
	public Boolean updateInvestigationDetails(InvestigationEntityDetails investigationDetails);
}
