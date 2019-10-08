package com.example.tfgestudiomedico2019.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.tfgestudiomedico2019.business.user.UserBusiness;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.model.rest.ResponseDto;
import com.example.tfgestudiomedico2019.model.rest.UserDto;
import com.example.tfgestudiomedico2019.model.rest.UserListDto;

@RestController
public class AdminControllerImpl implements AdminController{
	
	@Autowired
	private UserBusiness userBusiness;

	@Override
	public ResponseEntity<?> getAllUsers() {
		List<UserEntity> listResearchers = this.userBusiness.getAllResearchers();
		
		UserListDto listDto = new UserListDto();
		
		for(UserEntity elem: listResearchers) {
			listDto.getList().add(new UserDto(elem.getUsername(), elem.getName()));	
		}
		
		return new ResponseEntity<>(listDto, HttpStatus.OK);
	}

}
