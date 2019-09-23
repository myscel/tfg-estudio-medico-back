package com.example.tfgestudiomedico2019.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class HolaMundoController {
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String empezar() {
        return "Hola Mundo";
        
        
    }

}
