package com.example.tfgestudiomedico2019.business.user;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.tfgestudiomedico2019.model.entity.Role;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.repository.UserRepository;


@Service
@Transactional
public class UserBusinessImpl implements UserBusiness {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    
    @Override
    public UserEntity saveUser(UserEntity user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserEntity findByUsername(String username){
        return userRepository.findByUsername(username);
    }

	@Override
	public List<UserEntity> getAllResearchers() {
		return this.userRepository.findByRole(Role.RESEARCHER.name());
	}

	@Override
	public boolean deleteResearcher(String username) {
		if(this.userRepository.deleteByUsername(username) == 1) {
			return true;
		}
		return false;
	}

	@Override
	public UserEntity findById(Integer id) {
		// TODO Auto-generated method stub
		return this.userRepository.findById(id);
	}

}
