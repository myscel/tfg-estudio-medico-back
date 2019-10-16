package com.example.tfgestudiomedico2019.controller.researcher;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/researcher")
public interface ResearcherController {
	  @GetMapping(path = "/{id}/subjects", produces = "application/json")
	    public ResponseEntity<?> getSubjectsAndInvestigationsFromIdResearcher(@PathVariable String id);
}
