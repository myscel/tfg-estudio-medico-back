package com.example.tfgestudiomedico2019.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tfgestudiomedico2019.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	UserEntity findByDniAndPassword(String dni, String password);
}
