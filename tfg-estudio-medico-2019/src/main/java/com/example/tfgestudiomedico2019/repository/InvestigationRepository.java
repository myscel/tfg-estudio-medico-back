package com.example.tfgestudiomedico2019.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tfgestudiomedico2019.model.entity.InvestigationEntity;
import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;


/**
 * Interface to generate Investigation queries.
 */
@Repository
public interface InvestigationRepository extends JpaRepository<InvestigationEntity, Long> {
	InvestigationEntity findBySubjectAndNumberInvestigation(SubjectEntity subject, Integer numberInvestigation);
	List<InvestigationEntity> findBySubject(SubjectEntity subject);

}
