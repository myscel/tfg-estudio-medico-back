package com.example.tfgestudiomedico2019.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaMundoController {
	
	@RequestMapping(value = "/inicio", method = RequestMethod.GET)
    public String empezar() {
        return "Hola Mundo";
    }

}
