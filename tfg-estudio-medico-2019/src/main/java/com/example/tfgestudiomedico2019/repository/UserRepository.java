package com.example.tfgestudiomedico2019.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.tfgestudiomedico2019.model.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
    UserEntity findByUsernameAndPassword(String username, String password);
    UserEntity findByUsername(String username);
    UserEntity findById(Integer id);
    List<UserEntity> findByRole(String role);
	Long deleteByUsername(String username);
}
