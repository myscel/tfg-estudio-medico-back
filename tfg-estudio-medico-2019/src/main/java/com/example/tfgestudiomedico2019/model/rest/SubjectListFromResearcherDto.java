package com.example.tfgestudiomedico2019.model.rest;

import java.util.ArrayList;
import java.util.List;

public class SubjectListFromResearcherDto {
	private List<SubjectFromResearcherDto> list = new ArrayList<>();

	public List<SubjectFromResearcherDto> getList() {
		return list;
	}

	public void setList(List<SubjectFromResearcherDto> list) {
		this.list = list;
	}
}
