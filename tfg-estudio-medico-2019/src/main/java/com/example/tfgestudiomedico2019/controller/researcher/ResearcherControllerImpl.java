package com.example.tfgestudiomedico2019.controller.researcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.tfgestudiomedico2019.business.researcher.ResearcherBusiness;
import com.example.tfgestudiomedico2019.business.researcher.ResearcherBusinessImpl;
import com.example.tfgestudiomedico2019.business.subject.SubjectBusiness;
import com.example.tfgestudiomedico2019.model.entity.Role;
import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.model.rest.ResponseDto;
import com.example.tfgestudiomedico2019.model.rest.SubjectInfoDto;
import com.example.tfgestudiomedico2019.model.rest.SubjectListFromResearcherDto;
import com.example.tfgestudiomedico2019.model.rest.UserDto;

@RestController
public class ResearcherControllerImpl implements ResearcherController {
	
	@Autowired
	private ResearcherBusiness researcherBusiness;
	@Autowired
	private SubjectBusiness subjectBusiness;

	@Override
	public ResponseEntity<?> getSubjectsAndInvestigationsFromIdResearcher(String id) {
		try {
			SubjectListFromResearcherDto list = this.researcherBusiness.getAllSubjectsAndInvestigationsByResearcher(Integer.parseInt(id));
	        return new ResponseEntity<>(list, HttpStatus.OK);
		}
		catch(NumberFormatException e) {
	         return new ResponseEntity<>(new ResponseDto("El id no es un número entero"), HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
	        return new ResponseEntity<>(new ResponseDto("Unknown error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
	}
	
	@Override
	public ResponseEntity<?> registerSubject(SubjectEntity subject) {
		
		try {
			 if(subjectBusiness.getSubjectFromIdentificationNumber(subject.getIdentificationNumber())!=null){
		         return new ResponseEntity<>(new ResponseDto("Error registering user..."), HttpStatus.CONFLICT);
		     }
		        
		     SubjectEntity subjectSaved = this.subjectBusiness.saveSubject(subject);
		        
		     if(subjectSaved == null) {
		         return new ResponseEntity<>(new ResponseDto("Error saving subject..."), HttpStatus.INTERNAL_SERVER_ERROR);	
		     }
		     
		     
		     SubjectInfoDto dto = null; //= new SubjectInfoDto(subjectSaved.getIdentificationNumber(), subjectSaved.getIdResearcher());
		     
		     
		     return new ResponseEntity<>(dto, HttpStatus.CREATED);
		}
		catch(Exception e) {
	         return new ResponseEntity<>(new ResponseDto("Unknown error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}

}
