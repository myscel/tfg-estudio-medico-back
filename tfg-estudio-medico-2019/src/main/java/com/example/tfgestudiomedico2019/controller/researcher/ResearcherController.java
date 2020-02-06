package com.example.tfgestudiomedico2019.controller.researcher;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.tfgestudiomedico2019.model.rest.investigation.InvestigationDetailsToRegisterDto;
import com.example.tfgestudiomedico2019.model.rest.investigation.InvestigationDetailsToShowDto;
import com.example.tfgestudiomedico2019.model.rest.investigation.InvestigationDetailsToShowListDto;
import com.example.tfgestudiomedico2019.model.rest.investigation.NumberInvestigationsCompletedSubjectDto;
import com.example.tfgestudiomedico2019.model.rest.subject.SubjectInfoDto;
import com.example.tfgestudiomedico2019.model.rest.subject.SubjectListFromResearcherDto;
import com.example.tfgestudiomedico2019.model.rest.user.ResponseDto;
import com.example.tfgestudiomedico2019.model.rest.user.UserDto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller for Role Researcher.
 */
@RequestMapping("/api/researcher")
public interface ResearcherController {
	
	@ApiOperation(value = "Get all subjects and investigations who belong to a researcher")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Successfully got subjects with their investigations", response = SubjectListFromResearcherDto.class),
    		@ApiResponse(code = 400, message = "Id format invalid", response = ResponseDto.class),
    		@ApiResponse(code = 500, message = "Server error", response = ResponseDto.class)
    })
	@GetMapping(path = "/{id}/subjects", produces = "application/json")
	public ResponseEntity<?> getSubjectsAndInvestigationsFromIdResearcher(@PathVariable String id);
	
	@ApiOperation(value = "Register a subject")
    @ApiResponses(value = {
    		@ApiResponse(code = 201, message = "Successfully subject registered", response= UserDto.class),
    		@ApiResponse(code = 409, message = "Subject already exists", response= ResponseDto.class),
    		@ApiResponse(code = 410, message = "Researcher doesn't exist", response= ResponseDto.class),
    		@ApiResponse(code = 500, message = "Server error", response= ResponseDto.class)
    })
    @PostMapping(path = "/registerSubject", produces = "application/json")
  	public ResponseEntity<?> registerSubject(@RequestBody SubjectInfoDto subject);
	
	@ApiOperation(value = "Get the number of investigations completed from a subject based on his/her identification number")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Successfully got number of investigations", response = NumberInvestigationsCompletedSubjectDto.class),
    		@ApiResponse(code = 500, message = "Server error", response = ResponseDto.class)
    })
    @GetMapping(path = "/investigationsCompletedSubjectResearcher/{identificationNumber}", produces = "application/json")
    public ResponseEntity<?> getNumberInvestigationsCompletedFromSubjectResearcher(@PathVariable String identificationNumber);
    
	@ApiOperation(value = "Register an investigation details")
    @ApiResponses(value = {
    		@ApiResponse(code = 201, message = "Successfully investigation details registered", response= ResponseDto.class),
    		@ApiResponse(code = 400, message = "Investigation not found", response= ResponseDto.class),
    		@ApiResponse(code = 409, message = "Investigation details already exists", response= ResponseDto.class),
    		@ApiResponse(code = 500, message = "Server error", response= ResponseDto.class)
    })
    @PostMapping(path = "/registerInvestigationDetails", produces = "application/json")
  	public ResponseEntity<?> registerInvestigationDetails(@RequestBody InvestigationDetailsToRegisterDto investigationDetailsToRegisterDto);
	
	@ApiOperation(value = "Get the investigation details based on the idSubject and numberInvestigation")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Investigation details found successfully", response = InvestigationDetailsToShowDto.class),
    		@ApiResponse(code = 400, message = "Inputs format invalid", response= ResponseDto.class),
    		@ApiResponse(code = 409, message = "Investigation details doesn't exists", response= ResponseDto.class),
    		@ApiResponse(code = 500, message = "Server error", response= ResponseDto.class)
    })
    @GetMapping(path = "/getInvestigationDetails/{idSubject}/{numberInvestigation}", produces = "application/json")
    public ResponseEntity<?> getInvestigationDetails(@PathVariable String idSubject, @PathVariable String numberInvestigation);
	
	@ApiOperation(value = "Get a list of investigation details based on the idSubject")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "List Investigation details found successfully", response = InvestigationDetailsToShowListDto.class),
    		@ApiResponse(code = 400, message = "Inputs format invalid", response= ResponseDto.class),
    		@ApiResponse(code = 409, message = "List Investigation details doesn't exists", response= ResponseDto.class),
    		@ApiResponse(code = 500, message = "Server error", response= ResponseDto.class)
    })
    @GetMapping(path = "/getAllInvestigationDetails/{idSubject}", produces = "application/json")
    public ResponseEntity<?> getAllInvestigationDetails(@PathVariable String idSubject);
}
