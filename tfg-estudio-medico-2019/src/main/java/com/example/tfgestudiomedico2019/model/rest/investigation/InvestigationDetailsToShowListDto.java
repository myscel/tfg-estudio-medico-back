package com.example.tfgestudiomedico2019.model.rest.investigation;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * Investigation details dto with a list of Investigation details.
 *
 */
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
