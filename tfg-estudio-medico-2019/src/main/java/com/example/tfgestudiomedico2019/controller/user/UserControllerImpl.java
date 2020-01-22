package com.example.tfgestudiomedico2019.controller.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.tfgestudiomedico2019.business.user.UserBusiness;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.model.rest.user.ResponseDto;
import com.example.tfgestudiomedico2019.model.rest.user.UserLoggedDto;
import com.example.tfgestudiomedico2019.model.rest.user.UserToLoginDto;

@RestController
public class UserControllerImpl implements UserController {
	
	@Autowired
	private UserBusiness userBusiness;

	
	@Override
	public ResponseEntity<?> login(UserToLoginDto userToLoginDto) {
		try {
	        UserEntity userLogged = userBusiness.findByUsernameAndPassword(userToLoginDto.getUsername(), userToLoginDto.getPassword() );
	        
	        if(userLogged == null) {
	            return new ResponseEntity<>(new ResponseDto("User Not found"), HttpStatus.CONFLICT);
	        }
	        ModelMapper mapper = new ModelMapper();
	        UserLoggedDto userLoggedDto  = mapper.map(userLogged, UserLoggedDto.class);

	        return new ResponseEntity<>(userLoggedDto, HttpStatus.OK);
		}
		catch(Exception e) {
	        return new ResponseEntity<>(new ResponseDto("Unknown error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

	}
}
