package com.example.tfgestudiomedico2019.business.subject;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tfgestudiomedico2019.model.entity.InvestigationEntity;
import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.model.rest.SubjectInfoDto;
import com.example.tfgestudiomedico2019.model.rest.SubjectInfoListDto;
import com.example.tfgestudiomedico2019.repository.SubjectRepository;
import com.example.tfgestudiomedico2019.repository.UserRepository;

@Service
@Transactional
public class SubjectBusinessImpl implements SubjectBusiness {
	
	@Autowired
	private SubjectRepository SubjectRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
    public SubjectEntity saveSubject(SubjectEntity subject){
        return SubjectRepository.save(subject);
    }

	@Override
	public Boolean deleteSubjectByIdentificationNumber(Integer identificationNumber) {
		if(this.SubjectRepository.deleteByIdentificationNumber(identificationNumber) == 1) {
			return true;
		}
		return false;
	}

	@Override
	public Integer getNumberInvestigationsCompletedFromSubject(Integer identificationNumber) {
		
		SubjectEntity subject = this.SubjectRepository.findByIdentificationNumber(identificationNumber);
		
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
	public SubjectInfoDto getSubjectFromIdentificationNumber(Integer identificationNumber) {
		SubjectEntity entity =  this.SubjectRepository.findByIdentificationNumber(identificationNumber);
		
		if(entity == null) {
			return null;
		}
		
		ModelMapper mapper = new ModelMapper();
		SubjectInfoDto dto  = mapper.map(entity, SubjectInfoDto.class);
		
		return dto;
	}

	
	@Override
	public SubjectInfoListDto getSubjectsFromDNIResearcher(String username) {
		
		SubjectInfoListDto dtoList = new SubjectInfoListDto();
		
		ModelMapper mapper = new ModelMapper();

		UserEntity researcher = this.userRepository.findByUsername(username);
		
		if(researcher == null) {
			return null;
		}
		
		List<SubjectEntity> subjects = researcher.getSubjects();
		
		if(subjects == null) {
			return null;
		}
			
		for(SubjectEntity elem: subjects) {
			dtoList.getList().add(mapper.map(elem,SubjectInfoDto.class));
		}
		
		return dtoList;
	}

}
