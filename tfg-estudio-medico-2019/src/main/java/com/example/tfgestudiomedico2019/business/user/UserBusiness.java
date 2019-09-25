package com.example.tfgestudiomedico2019.business.user;

import com.example.tfgestudiomedico2019.model.entity.UserEntity;


import java.util.List;

public interface UserBusiness {
    UserEntity saveUser(UserEntity user);

    UserEntity updateUser(UserEntity user);

    void deleteUser(Long userId);

    UserEntity findByUsername(String username);

    List<UserEntity> findAllUsers();

    Long numberOfUsers();
}
