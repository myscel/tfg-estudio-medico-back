package com.example.tfgestudiomedico2019.business.subject;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
