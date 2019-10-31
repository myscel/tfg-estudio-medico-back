package com.example.tfgestudiomedico2019.controller.researcher;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.tfgestudiomedico2019.model.rest.ResponseDto;
import com.example.tfgestudiomedico2019.model.rest.SubjectListFromResearcherDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@Api(value="Researcher Profile", authorizations = { @Authorization(value="apiKey") })
@RequestMapping("/api/researcher")
public interface ResearcherController {
	
	@ApiOperation(value = "Get all subjects and investigatios who belong to a researcher")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Successfully got subjects with their investigations", response = SubjectListFromResearcherDto.class),
    		@ApiResponse(code = 400, message = "Id format invalid", response = ResponseDto.class),
    		@ApiResponse(code = 500, message = "Server error", response = ResponseDto.class)
    })
	  @GetMapping(path = "/{id}/subjects", produces = "application/json")
	    public ResponseEntity<?> getSubjectsAndInvestigationsFromIdResearcher(@PathVariable String id);
}
