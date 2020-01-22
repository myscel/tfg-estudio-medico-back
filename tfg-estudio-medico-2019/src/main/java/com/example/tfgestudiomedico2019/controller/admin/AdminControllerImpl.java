package com.example.tfgestudiomedico2019.controller.admin;

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
import com.example.tfgestudiomedico2019.model.entity.Rol;
import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.model.rest.InvestigationToEditDto;
import com.example.tfgestudiomedico2019.model.rest.InvestigationToEditListDto;
import com.example.tfgestudiomedico2019.model.rest.NumberInvestigationsCompletedSubjectDto;
import com.example.tfgestudiomedico2019.model.rest.ResponseDto;
import com.example.tfgestudiomedico2019.model.rest.SubjectInfoDto;
import com.example.tfgestudiomedico2019.model.rest.SubjectInfoListDto;
import com.example.tfgestudiomedico2019.model.rest.SubjectToDeleteDto;
import com.example.tfgestudiomedico2019.model.rest.UserDto;
import com.example.tfgestudiomedico2019.model.rest.UserListDto;
import com.example.tfgestudiomedico2019.model.rest.UserToDeleteDto;
import com.example.tfgestudiomedico2019.model.rest.UserToRegisterDto;
import com.example.tfgestudiomedico2019.model.rest.UserToUpdateDto;

@RestController
public class AdminControllerImpl implements AdminController{
	
	@Autowired
	private UserBusiness userBusiness;
	
	@Autowired
	private ResearcherBusiness researcherBusiness;
	
	@Autowired
	private SubjectBusiness subjectBusiness;

	@Override
	public ResponseEntity<?> getAllUsers() {
		try {
			List<UserEntity> listResearchers = this.userBusiness.getAllResearchers();
			
			UserListDto listDto = new UserListDto();
			
			for(UserEntity elem: listResearchers) {
				listDto.getList().add(new UserDto(elem.getUsername(), elem.getName(), elem.getSurname(), elem.getGender(), elem.getId()));	
			}
			
			return new ResponseEntity<>(listDto, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(new ResponseDto("Error en el servidor"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> deleteResearcher(UserToDeleteDto userToDeleteDto) {
		try {
			UserEntity userToDelete = this.userBusiness.findByUsername(userToDeleteDto.getUsername());
			
			if(userToDelete == null || Rol.ADMIN.name().equals(userToDelete.getRole())) {
				return new ResponseEntity<>(new ResponseDto("Error al borrar el usuario"),HttpStatus.NOT_FOUND);
			}
			
			if(!userToDelete.getSubjects().isEmpty()) {
				return new ResponseEntity<>(new ResponseDto("El investigador tiene pacientes asociados"),HttpStatus.CONFLICT);
			}

			if(this.userBusiness.deleteResearcher(userToDeleteDto.getUsername())) {
				return new ResponseEntity<>(new ResponseDto("Usuario borrado correctamente"),HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(new ResponseDto("Error al borrar el usuario"),HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception e) {
			return new ResponseEntity<>(new ResponseDto("Error en el servidor"),HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	@Override
	public ResponseEntity<?> registerResearcher(UserToRegisterDto user) {
		try {
			 if(userBusiness.findByUsername(user.getUsername())!=null){
		         return new ResponseEntity<>(new ResponseDto("Error, el usuario ya existe"), HttpStatus.CONFLICT);
		     }
			 
		     UserEntity userToRegisterEntity = new UserEntity();
		     userToRegisterEntity.setName(user.getName());
		     userToRegisterEntity.setUsername(user.getUsername());
		     userToRegisterEntity.setGender(user.getGender());
		     userToRegisterEntity.setSurname(user.getSurname());
		     userToRegisterEntity.setPassword(user.getPassword());
		     userToRegisterEntity.setRole(Rol.RESEARCHER.name());
		        
		     UserEntity userSaved = this.userBusiness.saveUser(userToRegisterEntity);
		        
		     if(userSaved == null) {
		         return new ResponseEntity<>(new ResponseDto("Error en la base de datos"), HttpStatus.INTERNAL_SERVER_ERROR);	
		     }
		     
		     UserDto dto = new UserDto(userSaved.getUsername(), userSaved.getName(), userSaved.getSurname(), userSaved.getGender(), userSaved.getId());
		     
		     return new ResponseEntity<>(dto, HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<>(new ResponseDto("Error en el servidor"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	public ResponseEntity<?> getAllSubjects() {
		try {
			List<SubjectEntity> entityList = this.researcherBusiness.getAllSubjects();
			
			SubjectInfoListDto dtoList = new SubjectInfoListDto();
			
			for(SubjectEntity elem: entityList) {
				SubjectInfoDto dto = new SubjectInfoDto();
				dto.setIdentificationNumber(elem.getIdentificationNumber());
				dto.setUsernameResearcher(elem.getIdResearcher().getUsername());
				
				dtoList.getList().add(dto);	
			}
			
			return new ResponseEntity<>(dtoList, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(new ResponseDto("Error en el servidor"),HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	public ResponseEntity<?> deleteSubject(SubjectToDeleteDto subjectToDeleteDto) {
		try {
			
			if(subjectToDeleteDto == null) {
				return new ResponseEntity<>(new ResponseDto("Número de identificación no válido"),HttpStatus.BAD_REQUEST);

			}
			
			if(this.subjectBusiness.deleteSubjectByIdentificationNumber(subjectToDeleteDto.getIdentificationNumber())) {
				return new ResponseEntity<>(new ResponseDto("Usuario borrado correctamente!"),HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(new ResponseDto("Error al borrar el usuario"),HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception e) {
			return new ResponseEntity<>(new ResponseDto("Error en el servidor"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> getNumberInvestigationsCompletedFromSubject(String identificationNumber) {
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
	public ResponseEntity<?> getSubjectByIdentificationNumber(String identificationNumber) {
		try {
			SubjectEntity entity = this.subjectBusiness.getSubjectFromIdentificationNumber(Integer.parseInt(identificationNumber));
			
			if(entity == null) {
		        return new ResponseEntity<>( new ResponseDto("Error: no existe el paciente"), HttpStatus.NOT_FOUND);

			}
			
			ModelMapper mapper = new ModelMapper();
			SubjectInfoDto dto  = mapper.map(entity, SubjectInfoDto.class);

			return new ResponseEntity<>(dto, HttpStatus.OK);	
		}
		catch(NumberFormatException e) {
			ResponseDto response = new ResponseDto("Error: el número de identificación debe ser un entero");
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			return new ResponseEntity<>(new ResponseDto("Error en el servidor"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> getSubjectsFromDNIResearcher(String username) {
		
		
		try {
			List<SubjectEntity> subjects = this.subjectBusiness.getSubjectsFromDNIResearcher(username);
			
			if(subjects == null) {
		        return new ResponseEntity<>(new ResponseDto("Fallo en la base de datos"), HttpStatus.NOT_FOUND);

			}
			
			SubjectInfoListDto dtoList = new SubjectInfoListDto();	
			ModelMapper mapper = new ModelMapper();
				
			for(SubjectEntity elem: subjects) {
				dtoList.getList().add(mapper.map(elem,SubjectInfoDto.class));
			}
			
			return new ResponseEntity<>(dtoList, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(new ResponseDto("Error en el servidor"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> getResearcherFromId(String id) {
		try {
			UserEntity entity = this.userBusiness.findById(Integer.parseInt(id));
			
			if(entity == null || entity.getRole().equals(Rol.ADMIN.name())) {
		        return new ResponseEntity<>(new ResponseDto("Error: el usuario no existe"), HttpStatus.NOT_FOUND);
			}
			
			UserDto dto = new UserDto(entity.getUsername(), entity.getName(), entity.getSurname(), entity.getGender(), entity.getId());
			
	        return new ResponseEntity<>(dto, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(new ResponseDto("Error en el servidor"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> updateResearcher(UserToUpdateDto dto) {
		try {
			UserEntity userEntity = new UserEntity();
			userEntity.setName(dto.getName());
			userEntity.setSurname(dto.getSurname());
			userEntity.setPassword(dto.getPassword());
			userEntity.setId(Integer.parseInt(dto.getId()));
			
			UserEntity userUpdated = this.userBusiness.updateUser(userEntity);
			
			if(userUpdated == null) {
		         return new ResponseEntity<>(new ResponseDto("Error user not found..."), HttpStatus.NOT_FOUND);	
			}
			
			UserDto dtoReturn = new UserDto();
			dtoReturn.setId(userUpdated.getId());
			dtoReturn.setName(userUpdated.getName());
			dtoReturn.setSurname(userUpdated.getSurname());
			
	        return new ResponseEntity<>(dtoReturn, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(new ResponseDto("Error en el servidor"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> getAllCompletedInvestigations() {
		try {
			
			List<SubjectEntity> listSubjects = this.subjectBusiness.getAllSubjects();
			
			InvestigationToEditListDto list = new InvestigationToEditListDto();
			
			for(SubjectEntity subject: listSubjects) {
				for(InvestigationEntity investigation: subject.getInvestigations()) {
					if(investigation.getCompleted()) {
						InvestigationToEditDto data = new InvestigationToEditDto(subject.getIdentificationNumber(), investigation.getNumberInvestigation(), investigation.getInvestigationEntityDetails().getId());
						list.getList().add(data);
					}
				}
			}
			
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(new ResponseDto("Error en el servidor"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
