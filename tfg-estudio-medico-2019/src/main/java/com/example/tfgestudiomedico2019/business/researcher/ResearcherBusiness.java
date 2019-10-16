package com.example.tfgestudiomedico2019.business.researcher;


import com.example.tfgestudiomedico2019.model.rest.SubjectListFromResearcherDto;

public interface ResearcherBusiness {
	public SubjectListFromResearcherDto getAllSubjectsByResearcher(Integer idResearcher);
}
