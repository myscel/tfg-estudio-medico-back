package com.example.tfgestudiomedico2019.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.tfgestudiomedico2019.model.rest.ResponseDto;
import com.example.tfgestudiomedico2019.model.rest.UserDto;

@RestController
public class UserControllerImpl implements UserController {

	@Override
	public ResponseEntity<ResponseDto> loginUser(UserDto userDto) {
		System.out.println(userDto);
		
		return new ResponseEntity<>(new ResponseDto("User logueado con éxito"), HttpStatus.OK); 
	}

}
