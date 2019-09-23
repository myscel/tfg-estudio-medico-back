package com.example.tfgestudiomedico2019.controller.admin;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminControllerImpl implements AdminController {

	@Override
	public String loginAdmin() {
		return "Hola soy admin salu2";
	}

}
