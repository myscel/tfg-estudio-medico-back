package com.example.tfgestudiomedico2019.model.rest.subject;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class SubjectInfoListDto {
	@ApiModelProperty(value="The list of subjects")
	private List<SubjectInfoDto> list = new ArrayList<>();

	public List<SubjectInfoDto> getList() {
		return list;
	}

	public void setList(List<SubjectInfoDto> list) {
		this.list = list;
	}
}
