package com.example.tfgestudiomedico2019.controller.user;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.tfgestudiomedico2019.model.entity.UserEntity;

@RequestMapping("/api/user")
public interface UserController {
	
    @PostMapping(path = "/register", produces = "application/json")
	public ResponseEntity<?> register(@RequestBody UserEntity user);
    
    
    @GetMapping(path = "/login", produces = "application/json")
    public ResponseEntity<?> login(Principal principal);

}
