package com.example.tfgestudiomedico2019.controller.user;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/user")
public interface UserController {
	
    
    @GetMapping(path = "/login", produces = "application/json")
    public ResponseEntity<?> login(Principal principal);
    
    @GetMapping(path = "/pruebas", produces = "application/json")
    public ResponseEntity<?> prueba1();
    
    @GetMapping(path = "/pruebas2/{id}", produces = "application/json")
    public ResponseEntity<?> prueba2(@PathVariable String id);

}
