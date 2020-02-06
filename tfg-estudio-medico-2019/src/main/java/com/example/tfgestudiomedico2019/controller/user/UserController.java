package com.example.tfgestudiomedico2019.controller.user;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.tfgestudiomedico2019.model.rest.user.UserToLoginDto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * Controller for All API Users.
 */
@RequestMapping("/api/user")
public interface UserController {
	
    @ApiOperation(value = "Login user")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Successfully logued"),
    		@ApiResponse(code = 409, message = "Fail login"),
    		@ApiResponse(code = 409, message = "Internal server error")
    })
    @PostMapping(path = "/login", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody UserToLoginDto userToLoginDto);
}
