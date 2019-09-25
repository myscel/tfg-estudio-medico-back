package com.example.tfgestudiomedico2019.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tfgestudiomedico2019.model.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
    UserEntity findByUsername(String username);
}
