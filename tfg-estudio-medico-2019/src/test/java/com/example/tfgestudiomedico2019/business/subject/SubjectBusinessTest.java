package com.example.tfgestudiomedico2019.business.subject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.tfgestudiomedico2019.model.entity.InvestigationEntity;
import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.model.rest.SubjectInfoDto;
import com.example.tfgestudiomedico2019.model.rest.SubjectInfoListDto;
import com.example.tfgestudiomedico2019.repository.SubjectRepository;
import com.example.tfgestudiomedico2019.repository.UserRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class SubjectBusinessTest {
	
	@InjectMocks
	private SubjectBusinessImpl subjectBusinessImpl;
	
	@Mock
	private SubjectRepository subjectRepository;
	
	@Mock
	private UserRepository userRepository;
	
	
	@Test
	public void saveSubjectTest() {
		SubjectEntity subject = new SubjectEntity();
		when(subjectRepository.save(any())).thenReturn(subject);
		this.subjectBusinessImpl.saveSubject(subject);
		verify(subjectRepository, times(1)).save(any());
	}
	
	@Test
	public void deleteSubjectByIdentificationNumberOKTest() {
		Integer idNumber = 11111111;
		when(subjectRepository.deleteByIdentificationNumber(any())).thenReturn(1L);
		
		boolean response = this.subjectBusinessImpl.deleteSubjectByIdentificationNumber(idNumber);
		
		assertEquals(true, response);
		verify(subjectRepository, times(1)).deleteByIdentificationNumber(any());
	}
	
	@Test
	public void deleteSubjectByIdentificationNumberKOTest() {
		Integer idNumber = 11111111;
		when(subjectRepository.deleteByIdentificationNumber(any())).thenReturn(0L);
		
		boolean response = this.subjectBusinessImpl.deleteSubjectByIdentificationNumber(idNumber);
		
		assertEquals(false, response);
		verify(subjectRepository, times(1)).deleteByIdentificationNumber(any());
	}
	
	@Test
	public void getNumberInvestigationsCompletedFromSubjectInvestigationsNullTest() {
		Integer idNumber = 11111111;
		SubjectEntity subject = new SubjectEntity();
		subject.setInvestigations(null);
		
		when(subjectRepository.findByIdentificationNumber(any())).thenReturn(subject);
		
		Integer result = this.subjectBusinessImpl.getNumberInvestigationsCompletedFromSubject(idNumber);
		
		assertEquals(new Integer(0), result);
		verify(subjectRepository, times(1)).findByIdentificationNumber(any());
	}
	
	@Test
	public void getNumberInvestigationsCompletedFromSubjectInvestigationsEmptyTest() {
		Integer idNumber = 11111111;
		SubjectEntity subject = new SubjectEntity();
		List<InvestigationEntity> investigations = new ArrayList<>();
		subject.setInvestigations(investigations);
		
		when(subjectRepository.findByIdentificationNumber(any())).thenReturn(subject);
		
		Integer result = this.subjectBusinessImpl.getNumberInvestigationsCompletedFromSubject(idNumber);
		
		assertEquals(new Integer(0), result);
		verify(subjectRepository, times(1)).findByIdentificationNumber(any());
	}
	
	@Test
	public void getNumberInvestigationsCompletedFromSubjectInvestigationsNotEmptyNotCompletedTest() {
		Integer idNumber = 11111111;
		SubjectEntity subject = new SubjectEntity();
		List<InvestigationEntity> investigations = new ArrayList<>();
		InvestigationEntity entity1 = new InvestigationEntity();
		entity1.setCompleted(false);
		InvestigationEntity entity2 = new InvestigationEntity();
		entity2.setCompleted(false);
		investigations.add(entity1);
		investigations.add(entity2);
		subject.setInvestigations(investigations);
		
		when(subjectRepository.findByIdentificationNumber(any())).thenReturn(subject);
		
		Integer result = this.subjectBusinessImpl.getNumberInvestigationsCompletedFromSubject(idNumber);
		
		assertEquals(new Integer(0), result);
		verify(subjectRepository, times(1)).findByIdentificationNumber(any());
	}
	
	@Test
	public void getNumberInvestigationsCompletedFromSubjectInvestigationsNotEmptyCompletedTest() {
		Integer idNumber = 11111111;
		SubjectEntity subject = new SubjectEntity();
		List<InvestigationEntity> investigations = new ArrayList<>();
		InvestigationEntity entity1 = new InvestigationEntity();
		entity1.setCompleted(true);
		InvestigationEntity entity2 = new InvestigationEntity();
		entity2.setCompleted(true);
		investigations.add(entity1);
		investigations.add(entity2);
		subject.setInvestigations(investigations);
		
		when(subjectRepository.findByIdentificationNumber(any())).thenReturn(subject);
		
		Integer result = this.subjectBusinessImpl.getNumberInvestigationsCompletedFromSubject(idNumber);
		
		assertEquals(new Integer(2), result);
		verify(subjectRepository, times(1)).findByIdentificationNumber(any());
	}

	@Test
	public void getSubjectFromIdentificationNumberSubjectNotFoundTest() {
		Integer idNumber = 11111111;

		
		when(subjectRepository.findByIdentificationNumber(any())).thenReturn(null);
		
		SubjectInfoDto dto = this.subjectBusinessImpl.getSubjectFromIdentificationNumber(idNumber);
		
		assertEquals(null, dto);
		verify(subjectRepository, times(1)).findByIdentificationNumber(anyInt());
	}
	
	@Test
	public void getSubjectFromIdentificationNumberSubjectOKTest() {
		Integer idNumber = 11111111;
		SubjectEntity entity = new SubjectEntity();
		entity.setIdentificationNumber(idNumber);
		
		when(subjectRepository.findByIdentificationNumber(any())).thenReturn(entity);
		
		SubjectInfoDto dto = this.subjectBusinessImpl.getSubjectFromIdentificationNumber(idNumber);
		
		assertEquals(entity.getIdentificationNumber(), dto.getIdentificationNumber());
		verify(subjectRepository, times(1)).findByIdentificationNumber(any());
	}
	
	@Test
	public void getSubjectsFromDNIResearcherSubjectInfoListDtoNullTest() {
		String username = "47298046H";
		UserEntity researcher = null;
		
		when(userRepository.findByUsername(anyString())).thenReturn(researcher);
		
		SubjectInfoListDto dtoList = this.subjectBusinessImpl.getSubjectsFromDNIResearcher(username);
		assertEquals(null, dtoList);
		verify(userRepository, times(1)).findByUsername(any());

	}
	
	@Test
	public void getSubjectsFromDNIResearcherSubjectsNullTest() {
		String username = "47298046H";
		UserEntity researcher = new UserEntity();
		researcher.setSubjects(null);
		
		when(userRepository.findByUsername(anyString())).thenReturn(researcher);
		
		SubjectInfoListDto dtoList = this.subjectBusinessImpl.getSubjectsFromDNIResearcher(username);
		assertEquals(null, dtoList);
		verify(userRepository, times(1)).findByUsername(any());
	}
	
	@Test
	public void getSubjectsFromDNIResearcherSubjectInfoListDtoNotNullTest() {
		String username = "47298046H";
		UserEntity researcher = new UserEntity();
		
		List<SubjectEntity> subjects = new ArrayList<>();
		SubjectEntity e1 = new SubjectEntity();
		SubjectEntity e2 = new SubjectEntity();
		subjects.add(e1);
		subjects.add(e2);
		
		researcher.setSubjects(subjects);
		
		when(userRepository.findByUsername(anyString())).thenReturn(researcher);
		
		SubjectInfoListDto dtoList = this.subjectBusinessImpl.getSubjectsFromDNIResearcher(username);
		
		assertEquals(2, dtoList.getList().size());
		verify(userRepository, times(1)).findByUsername(any());
	}
}
