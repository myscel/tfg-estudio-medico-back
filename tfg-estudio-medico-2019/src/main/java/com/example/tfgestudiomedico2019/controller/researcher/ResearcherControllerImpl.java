package com.example.tfgestudiomedico2019.controller.researcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.tfgestudiomedico2019.business.researcher.ResearcherBusinessImpl;
import com.example.tfgestudiomedico2019.model.rest.SubjectListFromResearcherDto;

@RestController
public class ResearcherControllerImpl implements ResearcherController {
	
	@Autowired
	private ResearcherBusinessImpl researcherBusinessImpl;

	@Override
	public ResponseEntity<?> getSubjectsAndInvestigationsFromIdResearcher(String id) {
		SubjectListFromResearcherDto list = this.researcherBusinessImpl.getAllSubjectsAndInvestigationsByResearcher(Integer.parseInt(id));
        return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
