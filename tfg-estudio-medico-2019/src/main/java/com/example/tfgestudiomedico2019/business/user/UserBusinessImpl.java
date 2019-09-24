package com.example.tfgestudiomedico2019.business.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.repository.UserRepository;

@Service
public class UserBusinessImpl implements UserBusiness{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Boolean loginUser(UserEntity userEntity) {
		
		if(this.userRepository.findByDniAndPassword(userEntity.getDni(), userEntity.getPassword()) == null) {
			return false;
		}
		
		return true;
	}

	@Override
	public UserEntity findByDni(String dni) {
		return this.userRepository.findByDni(dni);
	}

	@Override
	public UserEntity saveUser(UserEntity user) {
		return this.userRepository.save(user);
	}

	@Override
	public List<UserEntity> getAllUsers() {
		return this.userRepository.findAll();
	}

}
