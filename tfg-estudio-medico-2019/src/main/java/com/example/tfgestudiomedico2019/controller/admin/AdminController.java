package com.example.tfgestudiomedico2019.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/api/admin")
public interface AdminController {
	
    @GetMapping(path = "/users", produces = "application/json")
	public ResponseEntity<?> getAllUsers();
    
    

}
