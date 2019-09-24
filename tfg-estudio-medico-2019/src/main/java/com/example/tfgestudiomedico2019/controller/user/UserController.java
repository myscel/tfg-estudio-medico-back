package com.example.tfgestudiomedico2019.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.model.rest.ResponseDto;
import com.example.tfgestudiomedico2019.model.rest.UserDto;



@RequestMapping("/api/user")
public interface UserController {
	
	@CrossOrigin
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<ResponseDto> loginUser(@RequestBody UserDto userDto);
	
	@CrossOrigin
	@RequestMapping(value = "/registration", method = RequestMethod.POST ,  produces = "application/json")
    public ResponseEntity<UserEntity> saveUser(@RequestBody UserDto userDto);

}
