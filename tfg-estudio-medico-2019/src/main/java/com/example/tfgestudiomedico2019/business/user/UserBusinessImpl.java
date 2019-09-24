package com.example.tfgestudiomedico2019.business.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.repository.UserRepository;

@Service
public class UserBusinessImpl implements UserBusiness{
	
	@Autowired
	private UserRepository userRepository;
	
	 @Autowired
	 private PasswordEncoder passwordEncoder;

	@Override
	public Boolean loginUser(UserEntity userEntity) {
		
		if(this.userRepository.findByUsernameAndPassword(userEntity.getUsername(), userEntity.getPassword()) == null) {
			return false;
		}
		
		return true;
	}

	@Override
	public UserEntity findByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}

	@Override
	public UserEntity saveUser(UserEntity user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return this.userRepository.save(user);
	}

	@Override
	public List<UserEntity> getAllUsers() {
		return this.userRepository.findAll();
	}

}
