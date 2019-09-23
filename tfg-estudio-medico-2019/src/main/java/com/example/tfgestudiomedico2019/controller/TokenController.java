package com.example.tfgestudiomedico2019.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tfgestudiomedico2019.model.entity.JwtUser;
import com.example.tfgestudiomedico2019.security.JwtGenerator;

@RestController
@RequestMapping("/token")
public class TokenController {
	
	@PostMapping()
	public String generate(@RequestBody final JwtUser jwtUser) {
		
		JwtGenerator jwtGenerator = new JwtGenerator();
		return jwtGenerator.generate(jwtUser);
	}

}
