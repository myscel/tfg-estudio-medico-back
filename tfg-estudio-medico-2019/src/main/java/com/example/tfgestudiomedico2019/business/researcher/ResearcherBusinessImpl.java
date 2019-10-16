package com.example.tfgestudiomedico2019.business.researcher;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.model.rest.SubjectFromResearcherDto;
import com.example.tfgestudiomedico2019.model.rest.SubjectListFromResearcherDto;
import com.example.tfgestudiomedico2019.repository.ResearcherRepository;

@Service
@Transactional
public class ResearcherBusinessImpl implements ResearcherBusiness {
	
	@Autowired
	private ResearcherRepository researcherRepository;

	@Override
	public SubjectListFromResearcherDto getAllSubjectsAndInvestigationsByResearcher(Integer idResearcher) {
		UserEntity researcher = new UserEntity();
		researcher.setId(idResearcher);
		
		List<SubjectEntity> list = this.researcherRepository.findByResearcher(researcher);
		
		SubjectListFromResearcherDto dtoList = new SubjectListFromResearcherDto();
		
		for(SubjectEntity subject: list) {
			SubjectFromResearcherDto dto = new SubjectFromResearcherDto();
			
			dto.setIdentificationNumber(subject.getIdentificationNumber());
			
			if(subject.getInvestigations().get(0).getNumberInvestigation() == 1) {
				dto.setFirstInvestigationCompleted(subject.getInvestigations().get(0).getCompleted());
				dto.setSecondInvestigationCompleted(subject.getInvestigations().get(1).getCompleted());
			}
			else {
				dto.setFirstInvestigationCompleted(subject.getInvestigations().get(1).getCompleted());
				dto.setSecondInvestigationCompleted(subject.getInvestigations().get(0).getCompleted());
			}
			
			dtoList.getList().add(dto);
			
		}
		
		return dtoList;
	}

}
