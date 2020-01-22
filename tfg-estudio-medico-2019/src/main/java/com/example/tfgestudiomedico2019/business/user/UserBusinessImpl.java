package com.example.tfgestudiomedico2019.business.user;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tfgestudiomedico2019.model.entity.Rol;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.repository.UserRepository;


@Service
@Transactional
public class UserBusinessImpl implements UserBusiness {

    @Autowired
    private UserRepository userRepository;

    @Override
	public UserEntity findByUsernameAndPassword(String username, String password) {
    	return userRepository.findByUsernameAndPassword(username, password);
	}
    
    @Override
    public UserEntity saveUser(UserEntity user){
        return userRepository.save(user);
    }

    @Override
    public UserEntity findByUsername(String username){
        return userRepository.findByUsername(username);
    }

	@Override
	public List<UserEntity> getAllResearchers() {
		return this.userRepository.findByRole(Rol.RESEARCHER.name());
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
		return this.userRepository.findById(id);
	}

	@Override
	public UserEntity updateUser(UserEntity user) {	
		return this.userRepository.save(user);
	}
}
