package com.example.tfgestudiomedico2019.model.rest;

import java.util.ArrayList;
import java.util.List;

public class UserListDto {

	
	private List<UserDto> list = new ArrayList<>();

	public List<UserDto> getList() {
		return list;
	}

	public void setList(List<UserDto> list) {
		this.list = list;
	}
	
	
}
