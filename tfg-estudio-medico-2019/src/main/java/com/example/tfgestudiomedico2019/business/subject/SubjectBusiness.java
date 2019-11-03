package com.example.tfgestudiomedico2019.business.subject;

import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;
import com.example.tfgestudiomedico2019.model.rest.SubjectInfoDto;
import com.example.tfgestudiomedico2019.model.rest.SubjectInfoListDto;

public interface SubjectBusiness {
	public SubjectEntity saveSubject(SubjectEntity subject);
	public Boolean deleteSubjectByIdentificationNumber(Integer identificationNumber);
	public Integer getNumberInvestigationsCompletedFromSubject(Integer identificationNumber);
	public SubjectInfoDto getSubjectFromIdentificationNumber(Integer identificationNumber);
	public SubjectInfoListDto getSubjectsFromDNIResearcher(String username);
}
