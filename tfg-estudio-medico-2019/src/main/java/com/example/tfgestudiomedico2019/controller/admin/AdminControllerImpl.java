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
import com.example.tfgestudiomedico2019.model.entity.Role;
import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.model.rest.NumberInvestigationsCompletedSubjectDto;
import com.example.tfgestudiomedico2019.model.rest.ResponseDto;
import com.example.tfgestudiomedico2019.model.rest.SubjectInfoDto;
import com.example.tfgestudiomedico2019.model.rest.SubjectInfoListDto;
import com.example.tfgestudiomedico2019.model.rest.SubjectListFromResearcherDto;
import com.example.tfgestudiomedico2019.model.rest.UserDto;
import com.example.tfgestudiomedico2019.model.rest.UserListDto;
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
	         return new ResponseEntity<>(new ResponseDto("Unknown error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	@Override
	public ResponseEntity<?> deleteResearcher(String username) {
		
		try {
			UserEntity userToDelete = this.userBusiness.findByUsername(username);

			if(this.userBusiness.deleteResearcher(username)) {
				return new ResponseEntity<>(new ResponseDto("Usuario borrado correctamente!"),HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(new ResponseDto("Error al borrar el usuario"),HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception e) {
			return new ResponseEntity<>(new ResponseDto("Error en el srrvidor"),HttpStatus.INTERNAL_SERVER_ERROR);

		}	
	}

	@Override
	public ResponseEntity<?> registerResearcher(UserToRegisterDto user) {
		
		try {
			 if(userBusiness.findByUsername(user.getUsername())!=null){
		         return new ResponseEntity<>(new ResponseDto("Error registering user..."), HttpStatus.CONFLICT);
		     }
			 
		     UserEntity userToRegisterEntity = new UserEntity();
		     userToRegisterEntity.setName(user.getName());
		     userToRegisterEntity.setUsername(user.getUsername());
		     userToRegisterEntity.setGender(user.getGender());
		     userToRegisterEntity.setSurname(user.getSurname());
		     userToRegisterEntity.setPassword(user.getPassword());
		     userToRegisterEntity.setRole(Role.RESEARCHER.name());
		        
		     UserEntity userSaved = this.userBusiness.saveUser(userToRegisterEntity);
		        
		     if(userSaved == null) {
		         return new ResponseEntity<>(new ResponseDto("Error saving user..."), HttpStatus.INTERNAL_SERVER_ERROR);	
		     }
		     
		     UserDto dto = new UserDto(userSaved.getUsername(), userSaved.getName(), userSaved.getSurname(), userSaved.getGender(), userSaved.getId());
		     
		     
		     return new ResponseEntity<>(dto, HttpStatus.CREATED);
		}
		catch(Exception e) {
	         return new ResponseEntity<>(new ResponseDto("Unknown error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@Override
	public ResponseEntity<?> getAllSubjects() {
		
		try {
			SubjectInfoListDto list = this.researcherBusiness.getAllSubjects();
			
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
		catch(Exception e) {
	         return new ResponseEntity<>(new ResponseDto("Unknown error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@Override
	public ResponseEntity<?> getSubjectsAndInvestigationsFromIdAdmin(String id) {
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
	
	public ResponseEntity<?> deleteSubject(String identificationNumber) {
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
	public ResponseEntity<?> getNumberInvestigationsCompletedFromSubject(String identificationNumber) {
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

	@Override
	public ResponseEntity<?> getSubjectByIdentificationNumber(String identificationNumber) {
		try {
			SubjectInfoDto dto = this.subjectBusiness.getSubjectFromIdentificationNumber(Integer.parseInt(identificationNumber));
			
			if(dto == null) {
				ResponseDto response = new ResponseDto("Error: no existe el paciente");
		        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(dto, HttpStatus.OK);	
		}
		catch(NumberFormatException e) {
			ResponseDto response = new ResponseDto("Error: el número de identificación debe ser un entero");
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			ResponseDto response = new ResponseDto("Unknown error");
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> getSubjectsFromDNIResearcher(String username) {
		try {
			SubjectInfoListDto dtoList = this.subjectBusiness.getSubjectsFromDNIResearcher(username);
			
			if(dtoList == null) {
		        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(dtoList, HttpStatus.OK);
			
		}
		catch(Exception e) {
	        return new ResponseEntity<>(new ResponseDto("Unknown error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> getResearcherFromId(String id) {
		try {
			UserEntity entity = this.userBusiness.findById(Integer.parseInt(id));
			
			if(entity == null || entity.getRole().equals(Role.ADMIN.name())) {
		        return new ResponseEntity<>(new ResponseDto("Error: el usuario no existe"), HttpStatus.NOT_FOUND);
			}
			
			UserDto dto = new UserDto(entity.getUsername(), entity.getName(), entity.getSurname(), entity.getGender(), entity.getId());
			
			
	        return new ResponseEntity<>(dto, HttpStatus.OK);
		}
		catch(Exception e) {
	        return new ResponseEntity<>(new ResponseDto("Unknown error"), HttpStatus.INTERNAL_SERVER_ERROR);
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
	        return new ResponseEntity<>(new ResponseDto("Unknown error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
}
