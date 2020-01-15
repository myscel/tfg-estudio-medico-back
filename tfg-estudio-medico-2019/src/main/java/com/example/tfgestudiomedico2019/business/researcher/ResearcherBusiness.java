package com.example.tfgestudiomedico2019.business.researcher;


import java.util.List;

import com.example.tfgestudiomedico2019.model.entity.InvestigationEntity;
import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;
import com.example.tfgestudiomedico2019.model.rest.SubjectInfoListDto;
import com.example.tfgestudiomedico2019.model.rest.SubjectListFromResearcherDto;

public interface ResearcherBusiness {
	public SubjectListFromResearcherDto getAllSubjectsAndInvestigationsByResearcher(Integer idResearcher);
    public SubjectInfoListDto getAllSubjects();
    public InvestigationEntity getInvestigationBySubjectAndNumberInvestigation(Integer idSubject, Integer numberInvestigation);
    public Boolean saveInvestigationDetails(InvestigationEntity investigationEntity);
	public List<SubjectEntity> getAllSubjectsByResearcher(Integer idResearcher);


}
