package com.example.tfgestudiomedico2019.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.tfgestudiomedico2019.model.rest.investigation.InvestigationDetailsToUpdateDto;
import com.example.tfgestudiomedico2019.model.rest.investigation.InvestigationToEditListDto;
import com.example.tfgestudiomedico2019.model.rest.investigation.NumberInvestigationsCompletedSubjectDto;
import com.example.tfgestudiomedico2019.model.rest.subject.SubjectInfoDto;
import com.example.tfgestudiomedico2019.model.rest.subject.SubjectInfoListDto;
import com.example.tfgestudiomedico2019.model.rest.subject.SubjectToDeleteDto;
import com.example.tfgestudiomedico2019.model.rest.user.ResponseDto;
import com.example.tfgestudiomedico2019.model.rest.user.UserDto;
import com.example.tfgestudiomedico2019.model.rest.user.UserListDto;
import com.example.tfgestudiomedico2019.model.rest.user.UserToDeleteDto;
import com.example.tfgestudiomedico2019.model.rest.user.UserToRegisterDto;
import com.example.tfgestudiomedico2019.model.rest.user.UserToUpdateDto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


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
    		@ApiResponse(code = 409, message = "User has subjects under his/her care", response = ResponseDto.class),
    		@ApiResponse(code = 500, message = "Server error", response = ResponseDto.class)
    })
    @PostMapping(path = "/deleteResearcher", produces = "application/json")
	public ResponseEntity<?> deleteResearcher(@RequestBody UserToDeleteDto userToDeleteDto);
    
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
   
	@ApiOperation(value = "Delete a subject based on his/her identification number")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Successfully deleted subject", response = ResponseDto.class),
    		@ApiResponse(code = 400, message = "Id format invalid", response = ResponseDto.class),
    		@ApiResponse(code = 404, message = "Subject not found", response = ResponseDto.class),
    		@ApiResponse(code = 500, message = "Server error", response = ResponseDto.class)
    })
    @PostMapping(path = "/deleteSubject", produces = "application/json")
   	public ResponseEntity<?> deleteSubject(@RequestBody SubjectToDeleteDto subjectToDeleteDto);
    
	@ApiOperation(value = "Get the number of investigations completed from a subject based on his/her identification number")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Successfully got number of investigations", response = NumberInvestigationsCompletedSubjectDto.class),
    		@ApiResponse(code = 400, message = "Identification number format invalid", response = ResponseDto.class),
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
    		@ApiResponse(code = 400, message = "Id format invalid", response= ResponseDto.class),
    		@ApiResponse(code = 404, message = "Researcher doesn't exists", response= ResponseDto.class),
    		@ApiResponse(code = 500, message = "Server error", response= ResponseDto.class)
    })
    @PostMapping(path = "/updateResearcher", produces = "application/json")
  	public ResponseEntity<?> updateResearcher(@RequestBody UserToUpdateDto user);
	
	@ApiOperation(value = "Get all investigations completed")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Successfully got investigations", response= InvestigationToEditListDto.class),
    		@ApiResponse(code = 500, message = "Server error", response= ResponseDto.class)
    })
    @GetMapping(path = "/completedAppointments", produces = "application/json")
  	public ResponseEntity<?> getAllCompletedInvestigations();
	
	@ApiOperation(value = "Get all investigations completed")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Successfully got investigations", response= InvestigationToEditListDto.class),
    		@ApiResponse(code = 500, message = "Server error", response= ResponseDto.class)
    })
    @GetMapping(path = "/getAppointmentDetails/{investigationsDetailsId}", produces = "application/json")
  	public ResponseEntity<?> getAppointmentDetails(@PathVariable String investigationsDetailsId);
    
	@ApiOperation(value = "Update an investigation details")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Successfully investigation details updated", response= ResponseDto.class),
    		@ApiResponse(code = 500, message = "Server error", response= ResponseDto.class)
    })
    @PostMapping(path = "/updateInvestigationDetails", produces = "application/json")
  	public ResponseEntity<?> updateInvestigationDetails(@RequestBody InvestigationDetailsToUpdateDto investigationDetailsToUpdate);
}
