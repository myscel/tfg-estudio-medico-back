package com.example.tfgestudiomedico2019.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.model.rest.NumberInvestigationsCompletedSubjectDto;
import com.example.tfgestudiomedico2019.model.rest.ResponseDto;
import com.example.tfgestudiomedico2019.model.rest.SubjectInfoDto;
import com.example.tfgestudiomedico2019.model.rest.SubjectInfoListDto;
import com.example.tfgestudiomedico2019.model.rest.SubjectListFromResearcherDto;
import com.example.tfgestudiomedico2019.model.rest.UserDto;
import com.example.tfgestudiomedico2019.model.rest.UserListDto;
import com.example.tfgestudiomedico2019.model.rest.UserToRegisterDto;
import com.example.tfgestudiomedico2019.model.rest.UserToUpdateDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;


@Api(value="Admin Profile", authorizations = { @Authorization(value="apiKey") })
@RequestMapping("/api/admin")
public interface AdminController {
	
	@ApiOperation(value = "Get all users")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Successfully got all the users", response = UserListDto.class),
    		@ApiResponse(code = 500, message = "Server error", response = ResponseDto.class)
    })
    @GetMapping(path = "/users", produces = "application/json")
	public ResponseEntity<?> getAllUsers();
    
	@ApiOperation(value = "Delete a researcher based on dni")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Successfully user deleted", response = ResponseDto.class),
    		@ApiResponse(code = 404, message = "User not found", response = ResponseDto.class),
    		@ApiResponse(code = 500, message = "Server error", response = ResponseDto.class)
    })
    @DeleteMapping(path = "/deleteResearcher", produces = "application/json")
	public ResponseEntity<?> deleteResearcher(@RequestParam String username);
    
	@ApiOperation(value = "Register a researcher")
    @ApiResponses(value = {
    		@ApiResponse(code = 201, message = "Successfully user registered", response= UserDto.class),
    		@ApiResponse(code = 409, message = "User already exists", response= ResponseDto.class),
    		@ApiResponse(code = 500, message = "Server error", response= ResponseDto.class)
    })
    @PostMapping(path = "/registerResearcher", produces = "application/json")
  	public ResponseEntity<?> registerResearcher(@RequestBody UserToRegisterDto user);
    
	@ApiOperation(value = "Get all subjects")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Successfully got subjects", response = SubjectInfoListDto.class),
    		@ApiResponse(code = 500, message = "Server error", response = ResponseDto.class)
    })
    @GetMapping(path = "/subjects", produces = "application/json")
   	public ResponseEntity<?> getAllSubjects();
    
	@ApiOperation(value = "Get all subjects and investigatios who belong to a admin researcher")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Successfully got subjects with their investigations", response = SubjectListFromResearcherDto.class),
    		@ApiResponse(code = 400, message = "Id format invalid", response = ResponseDto.class),
    		@ApiResponse(code = 500, message = "Server error", response = ResponseDto.class)
    })
    @GetMapping(path = "/{id}/subjects", produces = "application/json")
    public ResponseEntity<?> getSubjectsAndInvestigationsFromIdAdmin(@PathVariable String id);
    
	@ApiOperation(value = "Delete a subject based on his/her identification number")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Successfully deleted subject", response = ResponseDto.class),
    		@ApiResponse(code = 400, message = "Id format invalid", response = ResponseDto.class),
    		@ApiResponse(code = 404, message = "Subject not found", response = ResponseDto.class),
    		@ApiResponse(code = 500, message = "Server error", response = ResponseDto.class)
    })
    @DeleteMapping(path = "/deleteSubject", produces = "application/json")
   	public ResponseEntity<?> deleteSubject(@RequestParam String identificationNumber);
    
	@ApiOperation(value = "Get the number of investigations completed from a subject based on his/her identification number")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Successfully got number of investigations", response = NumberInvestigationsCompletedSubjectDto.class),
    		@ApiResponse(code = 400, message = "Identification number format invalid", response = ResponseDto.class),
    		@ApiResponse(code = 404, message = "Subject not found", response = ResponseDto.class),
    		@ApiResponse(code = 500, message = "Server error", response = ResponseDto.class)
    })
    @GetMapping(path = "/investigationsCompletedSubject/{identificationNumber}", produces = "application/json")
    public ResponseEntity<?> getNumberInvestigationsCompletedFromSubject(@PathVariable String identificationNumber);
    
	@ApiOperation(value = "Get a subject based on her/his identification number")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Successfully got subject", response = SubjectInfoDto.class),
    		@ApiResponse(code = 400, message = "Identification number format invalid", response = ResponseDto.class),
    		@ApiResponse(code = 404, message = "Subject not found", response = ResponseDto.class),
    		@ApiResponse(code = 500, message = "Server error", response = ResponseDto.class)
    })
    @GetMapping(path = "/subjects/{identificationNumber}", produces = "application/json")
    public ResponseEntity<?> getSubjectByIdentificationNumber(@PathVariable String identificationNumber);
    
	@ApiOperation(value = "Get all subjects who belong to a researcher")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Successfully got subjects", response = SubjectInfoListDto.class),
    		@ApiResponse(code = 404, message = "Researcher not found", response = ResponseDto.class),
    		@ApiResponse(code = 500, message = "Server error", response = ResponseDto.class)
    })
    @GetMapping(path = "/{username}/subject", produces = "application/json")
    public ResponseEntity<?> getSubjectsFromDNIResearcher(@PathVariable String username);
    
	@ApiOperation(value = "Get a researcher based on her/his id")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Successfully got researcher", response = UserDto.class),
    		@ApiResponse(code = 404, message = "Researcher not found", response = ResponseDto.class),
    		@ApiResponse(code = 500, message = "Server error", response = ResponseDto.class)
    })
    @GetMapping(path = "/researchers/{id}", produces = "application/json")
    public ResponseEntity<?> getResearcherFromId(@PathVariable String id);
	
	@ApiOperation(value = "Update a researcher")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Researcher successfully updated", response= UserDto.class),
    		@ApiResponse(code = 404, message = "Researcher doesn't exists", response= ResponseDto.class),
    		@ApiResponse(code = 500, message = "Server error", response= ResponseDto.class)
    })
    @PostMapping(path = "/updateResearcher", produces = "application/json")
  	public ResponseEntity<?> updateResearcher(@RequestBody UserToUpdateDto user);
    
}
