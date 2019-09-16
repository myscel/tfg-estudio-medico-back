package com.example.tfgestudiomedico2019.controller.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.tfgestudiomedico2019.business.user.UserBusiness;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.model.rest.ResponseDto;
import com.example.tfgestudiomedico2019.model.rest.UserDto;

@RestController
public class UserControllerImpl implements UserController {

	@Autowired
	private UserBusiness userBusiness;
	
	
	@Override
	public ResponseEntity<ResponseDto> loginUser(UserDto userDto) {
		
		ModelMapper mapper = new ModelMapper();
		
		UserEntity user = mapper.map(userDto, UserEntity.class);
		
		if(this.userBusiness.loginUser(user)) {
			return new ResponseEntity<>(new ResponseDto("User logueado con éxito"), HttpStatus.OK); 
		}
		
		return new ResponseEntity<>(new ResponseDto("Fallo en el login"), HttpStatus.BAD_REQUEST); 
	}

}
