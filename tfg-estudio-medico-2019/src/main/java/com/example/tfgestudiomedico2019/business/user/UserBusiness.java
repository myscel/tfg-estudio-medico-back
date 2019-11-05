package com.example.tfgestudiomedico2019.business.user;

import java.util.List;

import com.example.tfgestudiomedico2019.model.entity.UserEntity;

public interface UserBusiness {
    public UserEntity saveUser(UserEntity user);
    public UserEntity findByUsername(String username);
    public UserEntity findById(Integer id);
    public List<UserEntity> getAllResearchers();
    public boolean deleteResearcher(String username);
    public UserEntity updateUser(UserEntity user);

    
}
