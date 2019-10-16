package com.example.tfgestudiomedico2019.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;

@Repository
public interface ResearcherRepository extends JpaRepository<SubjectEntity, Long> {
	List<SubjectEntity> findByResearcher(UserEntity researcher);	
}
