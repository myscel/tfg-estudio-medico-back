package com.example.tfgestudiomedico2019.business.user;

import com.example.tfgestudiomedico2019.model.entity.UserEntity;

public interface UserBusiness {
    public UserEntity saveUser(UserEntity user);
    public UserEntity findByUsername(String username);

}
