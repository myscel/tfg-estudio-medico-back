package com.example.tfgestudiomedico2019.business.subject;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tfgestudiomedico2019.model.entity.InvestigationEntity;
import com.example.tfgestudiomedico2019.model.entity.InvestigationEntityDetails;
import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.repository.InvestigationDetailsRepository;
import com.example.tfgestudiomedico2019.repository.SubjectRepository;
import com.example.tfgestudiomedico2019.repository.UserRepository;

@Service
@Transactional
public class SubjectBusinessImpl implements SubjectBusiness {
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private InvestigationDetailsRepository investigationDetailsRepository;
	
	
	@Override
    public SubjectEntity saveSubject(SubjectEntity subject){
        return subjectRepository.save(subject);
    }

	@Override
	public Boolean deleteSubjectByIdentificationNumber(Integer identificationNumber) {
		if(this.subjectRepository.deleteByIdentificationNumber(identificationNumber) == 1) {
			return true;
		}
		return false;
	}

	@Override
	public Integer getNumberInvestigationsCompletedFromSubject(Integer identificationNumber) {
		SubjectEntity subject = this.subjectRepository.findByIdentificationNumber(identificationNumber);
		List<InvestigationEntity> investigations = subject.getInvestigations();
		
		if(investigations == null || investigations.isEmpty()) {
			return 0;
		}
		int contInvestigationsCompleted = 0;
		
		for(InvestigationEntity elem: investigations) {
			if(elem.getCompleted()) {
				++contInvestigationsCompleted;
			}
		}
		return contInvestigationsCompleted;
	}

	@Override
	public SubjectEntity getSubjectFromIdentificationNumber(Integer identificationNumber) {
		return this.subjectRepository.findByIdentificationNumber(identificationNumber);
	}

	@Override
	public List<SubjectEntity> getSubjectsFromDNIResearcher(String username) {
		UserEntity researcher = this.userRepository.findByUsername(username);
		if(researcher == null) {
			return null;
		}
		List<SubjectEntity> subjects = researcher.getSubjects();
		
		return subjects;
	}

	@Override
	public List<SubjectEntity> getAllSubjects() {
		return this.subjectRepository.findAll();
	}

	@Override
	public InvestigationEntityDetails getInvestitgationDetailsFromId(Integer id) {
		return this.investigationDetailsRepository.findById(id);
	}

}
