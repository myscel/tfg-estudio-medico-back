package com.example.tfgestudiomedico2019.business.researcher;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tfgestudiomedico2019.model.entity.InvestigationEntity;
import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.repository.InvestigationRepository;
import com.example.tfgestudiomedico2019.repository.SubjectRepository;

/**
 * {@link ResearcherBusiness} implementation.
 */
@Service
@Transactional
public class ResearcherBusinessImpl implements ResearcherBusiness {
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private InvestigationRepository investigationRepository;
	

	@Override
	public List<SubjectEntity> getAllSubjectsAndInvestigationsByResearcher(Integer idResearcher) {
		UserEntity researcher = new UserEntity();
		researcher.setId(idResearcher);
		List<SubjectEntity> list = this.subjectRepository.findByResearcher(researcher);
		return list;
	}

	@Override
	public List<SubjectEntity> getAllSubjects() {
		List<SubjectEntity> entityList = this.subjectRepository.findAll();
		return entityList;
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

	@Override
	public List<SubjectEntity> getAllSubjectsByResearcher(Integer idResearcher) {
		UserEntity researcher = new UserEntity();
		researcher.setId(idResearcher);
		List<SubjectEntity> listSubjects = this.subjectRepository.findByResearcher(researcher);
		return listSubjects;
	}
}
