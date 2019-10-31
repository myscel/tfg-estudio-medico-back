package com.example.tfgestudiomedico2019.controller.user;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.tfgestudiomedico2019.model.rest.ResponseDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="Public")
@RequestMapping("/api/user")
public interface UserController {
	
    @ApiOperation(value = "Login and logout user")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Successfully logued"),
    		@ApiResponse(code = 500, message = "Server error", response = ResponseDto.class)
    })
    @GetMapping(path = "/login", produces = "application/json")
    public ResponseEntity<?> login(Principal principal);
}
