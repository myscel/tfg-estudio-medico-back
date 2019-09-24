package com.example.tfgestudiomedico2019.business.user;

import java.util.List;

import com.example.tfgestudiomedico2019.model.entity.UserEntity;

public interface UserBusiness {
	Boolean loginUser(UserEntity userEntity);
	
	UserEntity findByUsername(String username);
	
	UserEntity saveUser(UserEntity user);
	
	List<UserEntity> getAllUsers();
}
