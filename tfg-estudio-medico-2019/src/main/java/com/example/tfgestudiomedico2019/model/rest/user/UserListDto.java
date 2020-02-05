package com.example.tfgestudiomedico2019.model.rest.user;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * User dto that contains a list of  Users.
 * 
 */
public class UserListDto {

	@ApiModelProperty(value="The list of researchers")
	private List<UserDto> listResearchers = new ArrayList<>();
	
	@ApiModelProperty(value="The list of admins")
	private List<UserDto> listAdmins = new ArrayList<>();

	public List<UserDto> getListResearchers() {
		return listResearchers;
	}

	public void setListResearchers(List<UserDto> listResearchers) {
		this.listResearchers = listResearchers;
	}

	public List<UserDto> getListAdmins() {
		return listAdmins;
	}

	public void setListAdmins(List<UserDto> listAdmins) {
		this.listAdmins = listAdmins;
	}

	
}
