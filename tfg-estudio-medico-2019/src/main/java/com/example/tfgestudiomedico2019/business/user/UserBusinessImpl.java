package com.example.tfgestudiomedico2019.business.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.repository.UserRepository;

@Service
public class UserBusinessImpl implements UserBusiness{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserEntity loginUser(UserEntity userEntity) {	
		return this.userRepository.findByDniAndPassword(userEntity.getDni(), userEntity.getPassword());
	}

	@Override
	public UserEntity getUserByDni(String dni) {
		return this.userRepository.findByDni(dni);
	}

}
