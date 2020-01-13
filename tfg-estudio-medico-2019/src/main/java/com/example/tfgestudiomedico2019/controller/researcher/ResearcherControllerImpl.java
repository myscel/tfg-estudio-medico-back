package com.example.tfgestudiomedico2019.controller.researcher;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.tfgestudiomedico2019.business.researcher.ResearcherBusiness;
import com.example.tfgestudiomedico2019.business.researcher.ResearcherBusinessImpl;
import com.example.tfgestudiomedico2019.business.subject.SubjectBusiness;
import com.example.tfgestudiomedico2019.business.user.UserBusiness;
import com.example.tfgestudiomedico2019.model.entity.InvestigationEntity;
import com.example.tfgestudiomedico2019.model.entity.Role;
import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.model.rest.InvestigationDetailsToRegisterDto;
import com.example.tfgestudiomedico2019.model.rest.NumberInvestigationsCompletedSubjectDto;
import com.example.tfgestudiomedico2019.model.rest.ResponseDto;
import com.example.tfgestudiomedico2019.model.rest.SubjectInfoDto;
import com.example.tfgestudiomedico2019.model.rest.SubjectListFromResearcherDto;
import com.example.tfgestudiomedico2019.model.rest.SubjectToRegisterDto;
import com.example.tfgestudiomedico2019.model.rest.UserDto;

@RestController
public class ResearcherControllerImpl implements ResearcherController {
	
	@Autowired
	private ResearcherBusiness researcherBusiness;
	@Autowired
	private SubjectBusiness subjectBusiness;
	@Autowired
	private UserBusiness userBusiness;

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
	public ResponseEntity<?> registerSubject(SubjectToRegisterDto subject) {
		
		try {
			 if(subjectBusiness.getSubjectFromIdentificationNumber(Integer.parseInt(subject.getIdentificationNumber()))!=null){
		         return new ResponseEntity<>(new ResponseDto("Error registering user..."), HttpStatus.CONFLICT);
		     }
			 
			 //Set identification number
			 SubjectEntity subjectToSave = new SubjectEntity();
			 subjectToSave.setIdentificationNumber(Integer.parseInt(subject.getIdentificationNumber()));
			 
			 
			 //Set researcher
			 UserEntity user = userBusiness.findByUsername(subject.getUsernameResearcher());
			 if(userBusiness.findByUsername(subject.getUsernameResearcher())==null){
		         return new ResponseEntity<>(new ResponseDto("Error registering user..."), HttpStatus.GONE);
		     }
			 subjectToSave.setIdResearcher(user);
			 
			 //Set investigations
			 List<InvestigationEntity> investigations = new ArrayList<>();
			 InvestigationEntity investigation1 = new InvestigationEntity();
			 investigation1.setCompleted(false);
			 investigation1.setNumberInvestigation(1);
			 investigation1.setSubject(subjectToSave);
			 InvestigationEntity investigation2 = new InvestigationEntity();
			 investigation2.setCompleted(false);
			 investigation2.setNumberInvestigation(2);
			 investigation2.setSubject(subjectToSave);
			 investigations.add(investigation1);
			 investigations.add(investigation2);
			 
			 subjectToSave.setInvestigations(investigations);
			 
			 
		     SubjectEntity subjectSaved = this.subjectBusiness.saveSubject(subjectToSave);
		        
		     if(subjectSaved == null) {
		         return new ResponseEntity<>(new ResponseDto("Error saving subject..."), HttpStatus.INTERNAL_SERVER_ERROR);	
		     }
		     
		     SubjectInfoDto dto = new SubjectInfoDto(subjectSaved.getIdentificationNumber(), subjectSaved.getIdResearcher().getUsername());
		     
		     
		     return new ResponseEntity<>(dto, HttpStatus.CREATED);
		}
		catch(NumberFormatException e) {
	         return new ResponseEntity<>(new ResponseDto("El número de identificación del sujeto no es un número entero"), HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
	         return new ResponseEntity<>(new ResponseDto("Unknown error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}

	@Override
	public ResponseEntity<?> getNumberInvestigationsCompletedFromSubjectResearcher(String identificationNumber) {
		try {
			NumberInvestigationsCompletedSubjectDto dto = new NumberInvestigationsCompletedSubjectDto(this.subjectBusiness.getNumberInvestigationsCompletedFromSubject(Integer.parseInt(identificationNumber)));
			
			if(dto == null) {
		        return new ResponseEntity<>(new ResponseDto("Error: el paciente no existe"), HttpStatus.NOT_FOUND);
			}
			
	        return new ResponseEntity<>(dto, HttpStatus.OK);

		}
		catch(NumberFormatException e) {
	        return new ResponseEntity<>(new ResponseDto("Error: el número de identificación debe ser un entero"), HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
	        return new ResponseEntity<>(new ResponseDto("Unknown error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<?> deleteSubjectResearcher(String identificationNumber) {
		try {
			if(this.subjectBusiness.deleteSubjectByIdentificationNumber(Integer.parseInt(identificationNumber))) {
				return new ResponseEntity<>(new ResponseDto("Usuario borrado correctamente!"),HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(new ResponseDto("Error al borrar el usuario"),HttpStatus.NOT_FOUND);

			}
		}
		catch(NumberFormatException e) {
	        return new ResponseEntity<>(new ResponseDto("Error: el número de identificación debe ser un entero"), HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
	        return new ResponseEntity<>(new ResponseDto("Unknown error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> registerInvestigationDetails(InvestigationDetailsToRegisterDto investigationDetailsToRegisterDto) {
		System.out.println("DETALLES CITA: " + investigationDetailsToRegisterDto);
		
		InvestigationEntity investigationEntityToSave = this.researcherBusiness.getInvestigationBySubjectAndNumberInvestigation(investigationDetailsToRegisterDto.getIdSubject(), investigationDetailsToRegisterDto.getNumberInvestigation());
		
		
		
		System.out.println("CITA DE LA BBDD: " + investigationEntityToSave);
		
		if(investigationEntityToSave.getCompleted() || investigationEntityToSave.getSubject() != null) {
	        return new ResponseEntity<>(new ResponseDto("La cita ya está realizada"), HttpStatus.CONFLICT);
		}

		
		
        return new ResponseEntity<>(new ResponseDto("Investigación dada de alta"), HttpStatus.OK);

	}
}
