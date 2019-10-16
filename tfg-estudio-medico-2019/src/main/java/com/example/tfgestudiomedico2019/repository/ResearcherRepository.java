package com.example.tfgestudiomedico2019.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;

@Repository
public interface ResearcherRepository extends JpaRepository<SubjectEntity, Long> {
	
	@Query(
			  value = 	"SELECT * FROM investigation i JOIN subject s ON i.idsubject = s.id WHERE s.idresearcher = :idresearcher ORDER BY s.identificationnumber, i.numberinvestigation", 
			  nativeQuery = true
			)
	List<SubjectEntity> findAllPacientsFromResearcherOrderByIdentificationNumberAsc(@Param("idresearcher") Integer idresearcher);
	List<SubjectEntity> findByIdResearcher(UserEntity researcher);

	
	
	
	
}
