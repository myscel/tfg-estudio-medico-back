package com.example.tfgestudiomedico2019.controller.researcher;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.tfgestudiomedico2019.business.researcher.ResearcherBusiness;
import com.example.tfgestudiomedico2019.business.subject.SubjectBusiness;
import com.example.tfgestudiomedico2019.business.user.UserBusiness;
import com.example.tfgestudiomedico2019.model.entity.InvestigationEntity;
import com.example.tfgestudiomedico2019.model.entity.InvestigationEntityDetails;
import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.model.rest.investigation.InvestigationDetailsToRegisterDto;
import com.example.tfgestudiomedico2019.model.rest.investigation.InvestigationDetailsToShowDto;
import com.example.tfgestudiomedico2019.model.rest.investigation.InvestigationDetailsToShowListDto;
import com.example.tfgestudiomedico2019.model.rest.investigation.NumberInvestigationsCompletedSubjectDto;
import com.example.tfgestudiomedico2019.model.rest.subject.SubjectFromResearcherDto;
import com.example.tfgestudiomedico2019.model.rest.subject.SubjectInfoDto;
import com.example.tfgestudiomedico2019.model.rest.subject.SubjectListFromResearcherDto;
import com.example.tfgestudiomedico2019.model.rest.subject.SubjectToRegisterDto;
import com.example.tfgestudiomedico2019.model.rest.user.ResponseDto;

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
			List<SubjectEntity> list = this.researcherBusiness.getAllSubjectsAndInvestigationsByResearcher(Integer.parseInt(id));
			
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
			
	        return new ResponseEntity<>(dtoList, HttpStatus.OK);
		}
		catch(NumberFormatException e) {
	         return new ResponseEntity<>(new ResponseDto("El id no es un número entero"), HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			return new ResponseEntity<>(new ResponseDto("Error en el servidor"),HttpStatus.INTERNAL_SERVER_ERROR);
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
			return new ResponseEntity<>(new ResponseDto("Error en el servidor"),HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	@Override
	public ResponseEntity<?> getNumberInvestigationsCompletedFromSubjectResearcher(String identificationNumber) {
		try {
			NumberInvestigationsCompletedSubjectDto dto = new NumberInvestigationsCompletedSubjectDto(this.subjectBusiness.getNumberInvestigationsCompletedFromSubject(Integer.parseInt(identificationNumber)));
			
	        return new ResponseEntity<>(dto, HttpStatus.OK);

		}
		catch(NumberFormatException e) {
	        return new ResponseEntity<>(new ResponseDto("Error: el número de identificación debe ser un entero"), HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			return new ResponseEntity<>(new ResponseDto("Error en el servidor"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> registerInvestigationDetails(InvestigationDetailsToRegisterDto investigationDetailsToRegisterDto) {
		try {
			InvestigationEntity investigationEntityToSave = this.researcherBusiness.getInvestigationBySubjectAndNumberInvestigation(investigationDetailsToRegisterDto.getIdSubject(), investigationDetailsToRegisterDto.getNumberInvestigation());
			
			if(investigationEntityToSave == null) {
		        return new ResponseEntity<>(new ResponseDto("Error al cargar la cita"), HttpStatus.BAD_REQUEST);
			}
			
			if(investigationEntityToSave.getCompleted() || investigationEntityToSave.getInvestigationEntityDetails() != null) {
		        return new ResponseEntity<>(new ResponseDto("La cita ya está realizada"), HttpStatus.CONFLICT);
			}
			
			ModelMapper mapper = new ModelMapper();
			InvestigationEntityDetails investigationEntityDetails = mapper.map(investigationDetailsToRegisterDto, InvestigationEntityDetails.class);
			
			investigationEntityToSave.setInvestigationEntityDetails(investigationEntityDetails);
			investigationEntityToSave.setCompleted(true);
			
			if(!this.researcherBusiness.saveInvestigationDetails(investigationEntityToSave)) {
		        return new ResponseEntity<>(new ResponseDto("Error al guardar los detalles de la cita"), HttpStatus.BAD_REQUEST);
			}
			
	        return new ResponseEntity<>(new ResponseDto("Investigación dada de alta"), HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<>(new ResponseDto("Error en el servidor"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> getInvestigationDetails(String idSubject, String numberInvestigation) {
		try {
			int idSubjectAux = Integer.parseInt(idSubject);
			int numberInvestigationAux = Integer.parseInt(numberInvestigation);
			
			InvestigationEntity investigationEntity = this.researcherBusiness.getInvestigationBySubjectAndNumberInvestigation(idSubjectAux, numberInvestigationAux);

			if(investigationEntity == null) {
		        return new ResponseEntity<>(new ResponseDto("Error al obtener la cita"), HttpStatus.CONFLICT);
			}
			
			if(!investigationEntity.getCompleted() || investigationEntity.getInvestigationEntityDetails() == null) {
		        return new ResponseEntity<>(new ResponseDto("Error al obtener la cita"), HttpStatus.CONFLICT);
			}
			
			ModelMapper mapper = new ModelMapper();
			InvestigationDetailsToShowDto investigationDetailsToShowDto = mapper.map(investigationEntity.getInvestigationEntityDetails(), InvestigationDetailsToShowDto.class);
			investigationDetailsToShowDto.setIdentificationNumber(investigationEntity.getSubject().getIdentificationNumber());
			
	        return new ResponseEntity<>(investigationDetailsToShowDto, HttpStatus.OK);
		}
		catch(NumberFormatException e) {
	        return new ResponseEntity<>(new ResponseDto("Campos de entrada no válidos"), HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			return new ResponseEntity<>(new ResponseDto("Error en el servidor"),HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	@Override
	public ResponseEntity<?> getAllInvestigationDetails(String idSubject) {
		try {
			int idSubjectAux = Integer.parseInt(idSubject);
			
			List<SubjectEntity> listSubjects = this.researcherBusiness.getAllSubjectsByResearcher(idSubjectAux);

			if(listSubjects == null) {
		        return new ResponseEntity<>(new ResponseDto("Error al obtener la lista de citas"), HttpStatus.CONFLICT);
			}
			ModelMapper mapper = new ModelMapper();
			InvestigationDetailsToShowListDto investigationDetailsToShowListDto = new InvestigationDetailsToShowListDto();
			
			for(SubjectEntity subject: listSubjects) {
				for(InvestigationEntity elem: subject.getInvestigations()) {
					InvestigationDetailsToShowDto investigationDetailsToShowDto = new InvestigationDetailsToShowDto();
					if(elem.getCompleted()) {
						investigationDetailsToShowDto = mapper.map(elem.getInvestigationEntityDetails(), InvestigationDetailsToShowDto.class);
					}
					investigationDetailsToShowDto.setIdentificationNumber(subject.getIdentificationNumber());
					investigationDetailsToShowListDto.getList().add(investigationDetailsToShowDto);
				}
			}

	        return new ResponseEntity<>(investigationDetailsToShowListDto, HttpStatus.OK);
		}
		catch(NumberFormatException e) {
	        return new ResponseEntity<>(new ResponseDto("Campos de entrada no válidos"), HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			return new ResponseEntity<>(new ResponseDto("Error en el servidor"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
