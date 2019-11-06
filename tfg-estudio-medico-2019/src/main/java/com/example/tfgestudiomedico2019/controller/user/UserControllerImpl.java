package com.example.tfgestudiomedico2019.controller.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RestController;

import com.example.tfgestudiomedico2019.business.user.UserBusiness;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.security.JwtTokenProvider;

@RestController
public class UserControllerImpl implements UserController {
	
	
	@Autowired
	private UserBusiness userBusiness;
	
	@Autowired
    private JwtTokenProvider tokenProvider;
	
	
	@Override
	public ResponseEntity<?> login(Principal principal) {
		
		if(principal == null){
            //Will enter here when logout
            return new ResponseEntity<>(principal, HttpStatus.OK);
        }
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
        UserEntity userLogged = userBusiness.findByUsername(authenticationToken.getName());
        
        userLogged.setSubjects(null);
        
        userLogged.setToken(tokenProvider.generateJwtToken(authenticationToken));
        
        

        return new ResponseEntity<>(userLogged, HttpStatus.OK);
	}
}