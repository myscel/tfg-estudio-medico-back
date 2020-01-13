package com.example.tfgestudiomedico2019.business.researcher;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tfgestudiomedico2019.model.entity.InvestigationEntity;
import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.model.rest.SubjectFromResearcherDto;
import com.example.tfgestudiomedico2019.model.rest.SubjectInfoDto;
import com.example.tfgestudiomedico2019.model.rest.SubjectInfoListDto;
import com.example.tfgestudiomedico2019.model.rest.SubjectListFromResearcherDto;
import com.example.tfgestudiomedico2019.repository.InvestigationRepository;
import com.example.tfgestudiomedico2019.repository.SubjectRepository;

@Service
@Transactional
public class ResearcherBusinessImpl implements ResearcherBusiness {
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private InvestigationRepository investigationRepository;
	

	@Override
	public SubjectListFromResearcherDto getAllSubjectsAndInvestigationsByResearcher(Integer idResearcher) {
		UserEntity researcher = new UserEntity();
		researcher.setId(idResearcher);
		
		List<SubjectEntity> list = this.subjectRepository.findByResearcher(researcher);
		
		SubjectListFromResearcherDto dtoList = new SubjectListFromResearcherDto();
		
		for(SubjectEntity subject: list) {
			SubjectFromResearcherDto dto = new SubjectFromResearcherDto();
			
			dto.setIdentificationNumber(subject.getIdentificationNumber());
			dto.setId(subject.getId());
			
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

	@Override
	public SubjectInfoListDto getAllSubjects() {
		List<SubjectEntity> entityList = this.subjectRepository.findAll();
		
		SubjectInfoListDto dtoList = new SubjectInfoListDto();
		
		for(SubjectEntity elem: entityList) {
			SubjectInfoDto dto = new SubjectInfoDto();
			dto.setIdentificationNumber(elem.getIdentificationNumber());
			dto.setUsernameResearcher(elem.getIdResearcher().getUsername());
			
			dtoList.getList().add(dto);	
		}
		
		
		return dtoList;
	}

	@Override
	public InvestigationEntity getInvestigationBySubjectAndNumberInvestigation(Integer idSubject, Integer numberInvestigation) {
		SubjectEntity subjectEntity = new SubjectEntity();
		subjectEntity.setId(idSubject);
		
		InvestigationEntity investigationEntity =  this.investigationRepository.findBySubjectAndNumberInvestigation(subjectEntity, numberInvestigation);
		
		return investigationEntity;
	}

	@Override
	public Boolean saveInvestigationDetails(InvestigationEntity investigationEntity) {
		InvestigationEntity InvestigationSaved =  this.investigationRepository.save(investigationEntity);
		
		return InvestigationSaved != null;
	}
	
	
}
