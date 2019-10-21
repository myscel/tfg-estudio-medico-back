package com.example.tfgestudiomedico2019.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.tfgestudiomedico2019.business.researcher.ResearcherBusiness;
import com.example.tfgestudiomedico2019.business.user.UserBusiness;
import com.example.tfgestudiomedico2019.model.entity.Role;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.model.rest.ResponseDto;
import com.example.tfgestudiomedico2019.model.rest.SubjectInfoListDto;
import com.example.tfgestudiomedico2019.model.rest.UserDto;
import com.example.tfgestudiomedico2019.model.rest.UserListDto;

@RestController
public class AdminControllerImpl implements AdminController{
	
	@Autowired
	private UserBusiness userBusiness;
	
	@Autowired
	private ResearcherBusiness researcherBusiness;

	@Override
	public ResponseEntity<?> getAllUsers() {
		
		try {
			List<UserEntity> listResearchers = this.userBusiness.getAllResearchers();
			
			UserListDto listDto = new UserListDto();
			
			for(UserEntity elem: listResearchers) {
				listDto.getList().add(new UserDto(elem.getUsername(), elem.getName(), elem.getGender()));	
			}
			
			return new ResponseEntity<>(listDto, HttpStatus.OK);
		}
		catch(Exception e) {
			 ResponseDto response = new ResponseDto("Unknown error");
	         return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	@Override
	public ResponseEntity<?> deleteResearcher(String username) {
		
		UserEntity userToDelete = this.userBusiness.findByUsername(username);
		
		if(!userToDelete.getSubjects().isEmpty()) {
			return new ResponseEntity<>(new ResponseDto("El usuario tiene pacientes asocidados!"),HttpStatus.CONFLICT);
		}

		if(this.userBusiness.deleteResearcher(username)) {
			return new ResponseEntity<>(new ResponseDto("Usuario borrado correctamente!"),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(new ResponseDto("Error al borrar el usuario"),HttpStatus.NOT_FOUND);

		}
		
	}

	@Override
	public ResponseEntity<?> registerResearcher(UserEntity user) {
		
		try {
			 if(userBusiness.findByUsername(user.getUsername())!=null){
				 ResponseDto response = new ResponseDto("Error registering user...");
		         return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		     }


		     user.setRole(Role.RESEARCHER.name());
		        
		     UserEntity userSaved = this.userBusiness.saveUser(user);
		        
		     if(userSaved == null) {
		    	 ResponseDto response = new ResponseDto("Error saving user...");
		         return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		     }
		     
		     return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
		}
		catch(Exception e) {
			 ResponseDto response = new ResponseDto("Unknown error");
	         return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@Override
	public ResponseEntity<?> getAllSubjects() {
		
		try {
			SubjectInfoListDto list = this.researcherBusiness.getAllSubjects();
			
			
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
		catch(Exception e) {
			 ResponseDto response = new ResponseDto("Unknown error");
	         return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
}
