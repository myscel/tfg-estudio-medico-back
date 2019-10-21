package com.example.tfgestudiomedico2019.model.rest;

import java.util.ArrayList;
import java.util.List;

public class SubjectInfoListDto {
	private List<SubjectInfoDto> list = new ArrayList<>();

	public List<SubjectInfoDto> getList() {
		return list;
	}

	public void setList(List<SubjectInfoDto> list) {
		this.list = list;
	}
}
