package com.example.tfgestudiomedico2019.model.rest.user;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * User dto that contains a list of  Users.
 * 
 */
public class UserListDto {

	@ApiModelProperty(value="The list of users")
	private List<UserDto> list = new ArrayList<>();

	public List<UserDto> getList() {
		return list;
	}

	public void setList(List<UserDto> list) {
		this.list = list;
	}
}
