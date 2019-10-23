package com.example.tfgestudiomedico2019.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
	Long deleteByIdentificationNumber(Integer identificationNumber);
	SubjectEntity findByIdentificationNumber(Integer identificationNumber);
}
