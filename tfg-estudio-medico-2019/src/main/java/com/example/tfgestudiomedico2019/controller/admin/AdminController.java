package com.example.tfgestudiomedico2019.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tfgestudiomedico2019.model.entity.UserEntity;


@RequestMapping("/api/admin")
public interface AdminController {
	
    @GetMapping(path = "/users", produces = "application/json")
	public ResponseEntity<?> getAllUsers();
    
    @DeleteMapping(path = "/deleteResearcher", produces = "application/json")
	public ResponseEntity<?> deleteResearcher(@RequestParam String username);
    
    @PostMapping(path = "/registerResearcher", produces = "application/json")
  	public ResponseEntity<?> registerResearcher(@RequestBody UserEntity user);
    
    @GetMapping(path = "/subjects", produces = "application/json")
   	public ResponseEntity<?> getAllSubjects();
    
    

}
