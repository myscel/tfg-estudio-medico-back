package com.example.tfgestudiomedico2019.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tfgestudiomedico2019.business.user.UserBusiness;

@RestController
public class AdminController {

	@Autowired
	private UserBusiness userBusiness;
	
	@GetMapping("/api/admin/all")
	public ResponseEntity<?> findAllUsers(){
		return ResponseEntity.ok(this.userBusiness.getAllUsers());
	}
}
