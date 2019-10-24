package com.example.tfgestudiomedico2019.business.subject;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tfgestudiomedico2019.model.entity.InvestigationEntity;
import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;
import com.example.tfgestudiomedico2019.model.rest.SubjectInfoDto;
import com.example.tfgestudiomedico2019.repository.SubjectRepository;

@Service
@Transactional
public class SubjectBusinessImpl implements SubjectBusiness {
	
	@Autowired
	private SubjectRepository SubjectRepository;

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

}
