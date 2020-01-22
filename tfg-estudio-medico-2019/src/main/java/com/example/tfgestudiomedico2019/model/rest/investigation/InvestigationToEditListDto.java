package com.example.tfgestudiomedico2019.model.rest.investigation;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class InvestigationToEditListDto {
	@ApiModelProperty(value="The list of investigation completed to edit")
	private List<InvestigationToEditDto> list = new ArrayList<>();

	public List<InvestigationToEditDto> getList() {
		return list;
	}

	public void setList(List<InvestigationToEditDto> list) {
		this.list = list;
	}

}
