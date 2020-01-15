package com.example.tfgestudiomedico2019.model.rest;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class InvestigationDetailsToShowListDto {
	@ApiModelProperty(value="The list of investigation details")
	private List<InvestigationDetailsToShowDto> list = new ArrayList<>();

	public List<InvestigationDetailsToShowDto> getList() {
		return list;
	}

	public void setList(List<InvestigationDetailsToShowDto> list) {
		this.list = list;
	}
}
