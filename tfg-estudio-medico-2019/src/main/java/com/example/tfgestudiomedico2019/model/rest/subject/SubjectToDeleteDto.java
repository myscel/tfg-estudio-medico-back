package com.example.tfgestudiomedico2019.model.rest.subject;

import io.swagger.annotations.ApiModelProperty;

public class SubjectToDeleteDto {
	@ApiModelProperty(value = "The identification number of the subject", example = "12345678")
	private Integer identificationNumber;

	public Integer getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(Integer identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	@Override
	public String toString() {
		return "SubjectToDeleteDto [identificationNumber=" + identificationNumber + "]";
	}
}
