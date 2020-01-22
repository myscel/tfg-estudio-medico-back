package com.example.tfgestudiomedico2019.model.rest.subject;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class SubjectListFromResearcherDto {
	@ApiModelProperty(value = "The list of subjects investigated by the researcher")
	private List<SubjectFromResearcherDto> list = new ArrayList<>();

	public List<SubjectFromResearcherDto> getList() {
		return list;
	}

	public void setList(List<SubjectFromResearcherDto> list) {
		this.list = list;
	}
}
