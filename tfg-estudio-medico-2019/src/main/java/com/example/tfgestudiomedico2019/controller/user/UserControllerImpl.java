package com.example.tfgestudiomedico2019.controller.user;

import java.security.Principal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.tfgestudiomedico2019.business.user.UserBusiness;
import com.example.tfgestudiomedico2019.model.entity.Role;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.model.rest.ResponseDto;
import com.example.tfgestudiomedico2019.model.rest.UserDto;
import com.example.tfgestudiomedico2019.security.JwtTokenProvider;


@RestController
public class UserControllerImpl implements UserController {

	@Autowired
	private UserBusiness userBusiness;
	
	@Autowired
    private JwtTokenProvider jwtTokenProvider;
	
	
	@CrossOrigin
	@Override
	public ResponseEntity<ResponseDto> loginUser(UserDto userDto) {
		System.out.println(userDto);
		
		ModelMapper mapper = new ModelMapper();
		
		UserEntity user = mapper.map(userDto, UserEntity.class);
		
		if(this.userBusiness.loginUser(user)) {
			return new ResponseEntity<>(new ResponseDto("User logueado con éxito"), HttpStatus.OK); 
		}
		
		return new ResponseEntity<>(new ResponseDto("Fallo en el login"), HttpStatus.BAD_REQUEST); 
	}


	@Override
	public ResponseEntity<UserEntity> saveUser(@RequestBody UserDto userDto) {
		if(this.userBusiness.findByUsername(userDto.getUsername()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		ModelMapper mapper = new ModelMapper();
		
		UserEntity user = mapper.map(userDto, UserEntity.class);
		
		user.setRole(Role.USER.toString());
		
		return new ResponseEntity<>(this.userBusiness.saveUser(user), HttpStatus.OK);
		
	}


	@Override
	public ResponseEntity<?> login(Principal principal) {
		
		if(principal == null){
            //This should be ok http status because this will be used for logout path.
            return ResponseEntity.ok(principal);
        }
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
        UserEntity user = this.userBusiness.findByUsername(authenticationToken.getName());
        user.setToken(jwtTokenProvider.generateToken(authenticationToken));
        return new ResponseEntity<>(user, HttpStatus.OK);
		
	}

}
