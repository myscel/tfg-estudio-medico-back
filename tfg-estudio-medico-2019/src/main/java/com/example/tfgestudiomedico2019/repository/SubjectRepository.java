package com.example.tfgestudiomedico2019.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;

/**
 * Interface to generate Subject queries.
 */
@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
	Long deleteByIdentificationNumber(String identificationNumber);
	SubjectEntity findByIdentificationNumber(String identificationNumber);
	List<SubjectEntity> findByResearcher(UserEntity researcher);
	List<SubjectEntity> findAll();
}
