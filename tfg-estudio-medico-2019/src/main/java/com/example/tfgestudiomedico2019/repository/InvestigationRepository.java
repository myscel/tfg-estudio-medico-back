package com.example.tfgestudiomedico2019.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tfgestudiomedico2019.model.entity.InvestigationEntity;
import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;



@Repository
public interface InvestigationRepository extends JpaRepository<InvestigationEntity, Long> {
	InvestigationEntity findBySubjectAndNumberInvestigation(SubjectEntity subject, Integer numberInvestigation);

}
