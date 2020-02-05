package com.example.tfgestudiomedico2019.model.rest.subject;

import io.swagger.annotations.ApiModelProperty;

/**
 * Subject dto that contains the information to delete a subject.
 *
 */
public class SubjectToDeleteDto {
	@ApiModelProperty(value = "The identification number of the subject", example = "12345678", dataType = "java.lang.Integer")
	private String identificationNumber;

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	@Override
	public String toString() {
		return "SubjectToDeleteDto [identificationNumber=" + identificationNumber + "]";
	}
}
