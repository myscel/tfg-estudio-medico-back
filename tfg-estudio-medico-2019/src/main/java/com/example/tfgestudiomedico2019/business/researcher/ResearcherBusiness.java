package com.example.tfgestudiomedico2019.business.researcher;


import java.util.List;

import com.example.tfgestudiomedico2019.model.entity.InvestigationEntity;
import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;

public interface ResearcherBusiness {
	public List<SubjectEntity> getAllSubjectsAndInvestigationsByResearcher(Integer idResearcher);
    public List<SubjectEntity> getAllSubjects();
    public InvestigationEntity getInvestigationBySubjectAndNumberInvestigation(Integer idSubject, Integer numberInvestigation);
    public Boolean saveInvestigationDetails(InvestigationEntity investigationEntity);
	public List<SubjectEntity> getAllSubjectsByResearcher(Integer idResearcher);


}
