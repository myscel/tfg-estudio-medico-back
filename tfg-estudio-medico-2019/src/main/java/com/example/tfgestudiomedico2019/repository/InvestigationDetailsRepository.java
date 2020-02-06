package com.example.tfgestudiomedico2019.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tfgestudiomedico2019.model.entity.InvestigationEntityDetails;

/**
 * Interface to generate Investigation Details queries.
 */
@Repository
public interface InvestigationDetailsRepository extends JpaRepository<InvestigationEntityDetails, Long> {
	InvestigationEntityDetails findById(Integer id);
}
