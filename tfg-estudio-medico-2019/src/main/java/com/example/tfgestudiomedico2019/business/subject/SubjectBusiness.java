package com.example.tfgestudiomedico2019.business.subject;

import java.util.List;

import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;
import com.example.tfgestudiomedico2019.model.rest.SubjectInfoDto;
import com.example.tfgestudiomedico2019.model.rest.SubjectInfoListDto;

public interface SubjectBusiness {
	public SubjectEntity saveSubject(SubjectEntity subject);
	public Boolean deleteSubjectByIdentificationNumber(Integer identificationNumber);
	public Integer getNumberInvestigationsCompletedFromSubject(Integer identificationNumber);
	public SubjectEntity getSubjectFromIdentificationNumber(Integer identificationNumber);
	public List<SubjectEntity> getSubjectsFromDNIResearcher(String username);
	public List<SubjectEntity> getAllSubjects();
}
