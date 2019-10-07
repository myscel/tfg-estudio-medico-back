package com.example.tfgestudiomedico2019.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	
    UserEntity findByUsername(String username);
    List<UserEntity> findByRole(String role);
}
