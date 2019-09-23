package com.example.tfgestudiomedico2019.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.tfgestudiomedico2019.model.rest.ResponseDto;
import com.example.tfgestudiomedico2019.model.rest.UserDto;

@RequestMapping("/app/admin")
public interface AdminController {
	
	@CrossOrigin()
	//@Secured("ADMIN")
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/hola", method = RequestMethod.GET)
    public String loginAdmin();
	

}
