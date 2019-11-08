package com.example.tfgestudiomedico2019.business.researcher;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.tfgestudiomedico2019.model.entity.InvestigationEntity;
import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.model.rest.SubjectInfoListDto;
import com.example.tfgestudiomedico2019.model.rest.SubjectListFromResearcherDto;
import com.example.tfgestudiomedico2019.repository.SubjectRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ResearcherBusinessTest {
	
	@InjectMocks
	private ResearcherBusinessImpl researcherBusinessImpl;
	
	@Mock
	private SubjectRepository subjectRepository;
	
	@Test
	public void getAllSubjectsAndInvestigationsByResearcherEmptyListTest() {
		Integer idResearcher = 1;
		List<SubjectEntity> list = new ArrayList<>();
		
		when(this.subjectRepository.findByResearcher(any())).thenReturn(list);
		
		
		SubjectListFromResearcherDto dtoList = this.researcherBusinessImpl.getAllSubjectsAndInvestigationsByResearcher(idResearcher);
		verify(this.subjectRepository, times(1)).findByResearcher(any());
		assertEquals(dtoList.getList().size(), 0);
	}
	
	@Test
	public void getAllSubjectsAndInvestigationsByResearcherListWithDifferentInvestigationsTest() {
		Integer idResearcher = 1;
		List<SubjectEntity> list = new ArrayList<>();
		
		SubjectEntity e1 = new SubjectEntity();
		SubjectEntity e2 = new SubjectEntity();
		
		List<InvestigationEntity> l1 = new ArrayList<>();
		List<InvestigationEntity> l2 = new ArrayList<>();

		InvestigationEntity i1 = new InvestigationEntity();
		i1.setNumberInvestigation(1);
		i1.setCompleted(true);
		
		InvestigationEntity i2 = new InvestigationEntity();
		i2.setNumberInvestigation(2);
		i2.setCompleted(false);
		
		l1.add(i1);
		l1.add(i2);
		l2.add(i2);
		l2.add(i1);
		
		e1.setInvestigations(l1);
		e2.setInvestigations(l2);

		list.add(e1);
		list.add(e2);
		
		when(this.subjectRepository.findByResearcher(any())).thenReturn(list);
		
		
		SubjectListFromResearcherDto dtoList = this.researcherBusinessImpl.getAllSubjectsAndInvestigationsByResearcher(idResearcher);
		verify(this.subjectRepository, times(1)).findByResearcher(any());
	}

	@Test
	public void getAllSubjectsEmptyListTest() {
		List<SubjectEntity> entityList = new ArrayList<>();
		
		when(this.subjectRepository.findAll()).thenReturn(entityList);

		SubjectInfoListDto dtoList = this.researcherBusinessImpl.getAllSubjects();
		
		assertEquals(0, dtoList.getList().size());
		verify(this.subjectRepository, times(1)).findAll();
	}
	
	@Test
	public void getAllSubjectsOKTest() {
		List<SubjectEntity> entityList = new ArrayList<>();
		
		SubjectEntity e1 = new SubjectEntity();
		e1.setIdentificationNumber(11111111);
		UserEntity u1 = new UserEntity();
		u1.setUsername("47298046H");
		e1.setIdResearcher(u1);
		
		SubjectEntity e2 = new SubjectEntity();
		e2.setIdentificationNumber(2);
		UserEntity u2 = new UserEntity();
		u2.setUsername("12345678A");
		e2.setIdResearcher(u2);
		
		entityList.add(e1);
		entityList.add(e2);

		when(this.subjectRepository.findAll()).thenReturn(entityList);

		SubjectInfoListDto dtoList = this.researcherBusinessImpl.getAllSubjects();
		
		assertEquals(2, dtoList.getList().size());
		verify(this.subjectRepository, times(1)).findAll();
	}
}
