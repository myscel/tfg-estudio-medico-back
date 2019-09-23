package com.example.tfgestudiomedico2019.controller.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.tfgestudiomedico2019.business.user.UserBusiness;
import com.example.tfgestudiomedico2019.model.entity.JwtUser;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.model.rest.ResponseDto;
import com.example.tfgestudiomedico2019.model.rest.UserDto;
import com.example.tfgestudiomedico2019.security.JwtGenerator;

@RestController
public class UserControllerImpl implements UserController {

	@Autowired
	private UserBusiness userBusiness;
	
	@Autowired
	private JwtGenerator jwtGenerator;
	
	
	@CrossOrigin
	@Override
	public ResponseEntity<ResponseDto> loginUser(UserDto userDto) {
		System.out.println(userDto);
		
		ModelMapper mapper = new ModelMapper();
		
		UserEntity user = mapper.map(userDto, UserEntity.class);
		UserEntity userLogged = this.userBusiness.loginUser(user);
		
		
		
		if(userLogged != null) {
			//Crear Token
			JwtUser jwtUser = new JwtUser();
			jwtUser.setUsername(userLogged.getDni());
			jwtUser.setRole(userLogged.getRole().toString());
			String token = this.jwtGenerator.generate(jwtUser);
			return new ResponseEntity<>(new ResponseDto("User logueado con éxito: " + token), HttpStatus.OK); 
		}
		
		return new ResponseEntity<>(new ResponseDto("Fallo en el login"), HttpStatus.BAD_REQUEST); 
	}

}