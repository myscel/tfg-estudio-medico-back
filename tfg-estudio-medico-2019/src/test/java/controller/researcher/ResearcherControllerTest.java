package controller.researcher;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import com.example.tfgestudiomedico2019.business.researcher.ResearcherBusiness;
import com.example.tfgestudiomedico2019.business.subject.SubjectBusiness;
import com.example.tfgestudiomedico2019.business.user.UserBusiness;
import com.example.tfgestudiomedico2019.controller.researcher.ResearcherControllerImpl;
import com.example.tfgestudiomedico2019.model.entity.InvestigationEntity;
import com.example.tfgestudiomedico2019.model.entity.InvestigationEntityDetails;
import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.model.rest.investigation.InvestigationDetailsToRegisterDto;
import com.example.tfgestudiomedico2019.model.rest.investigation.InvestigationDetailsToShowDto;
import com.example.tfgestudiomedico2019.model.rest.investigation.InvestigationDetailsToShowListDto;
import com.example.tfgestudiomedico2019.model.rest.investigation.NumberInvestigationsCompletedSubjectDto;
import com.example.tfgestudiomedico2019.model.rest.subject.SubjectInfoDto;
import com.example.tfgestudiomedico2019.model.rest.subject.SubjectListFromResearcherDto;


@RunWith(MockitoJUnitRunner.class)
public class ResearcherControllerTest {
	
	@InjectMocks
	private ResearcherControllerImpl researcherControllerImpl;
	
	@Mock
	private ResearcherBusiness researcherBusiness;
	
	@Mock
	private SubjectBusiness subjectBusiness;
	
	@Mock
	private UserBusiness userBusiness;
	
	
	@Test
	public void getSubjectsAndInvestigationsFromIdResearcherExceptionTest() {
		String id = "24";
		
		when(this.researcherBusiness.getAllSubjectsAndInvestigationsByResearcher(any())).thenThrow(new IllegalArgumentException());
		ResponseEntity<?> response = this.researcherControllerImpl.getSubjectsAndInvestigationsFromIdResearcher(id);
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.researcherBusiness, times(1)).getAllSubjectsAndInvestigationsByResearcher(any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getSubjectsAndInvestigationsFromIdResearcherInvalidIdTest() {
		String id = "INVALID_ID";
		
		ResponseEntity<?> response = this.researcherControllerImpl.getSubjectsAndInvestigationsFromIdResearcher(id);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getSubjectsAndInvestigationsFromIdResearcherEmptyListTest() {
		String id = "24";
		List<SubjectEntity> list = new ArrayList<>();
		
		when(this.researcherBusiness.getAllSubjectsAndInvestigationsByResearcher(any())).thenReturn(list);
		ResponseEntity<?> response = this.researcherControllerImpl.getSubjectsAndInvestigationsFromIdResearcher(id);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(0, ((SubjectListFromResearcherDto)response.getBody()).getList().size());
		
		verify(this.researcherBusiness, times(1)).getAllSubjectsAndInvestigationsByResearcher(any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getSubjectsAndInvestigationsFromIdResearcherNotEmptyOrderedInvestigationsListTest() {
		String id = "24";
		List<SubjectEntity> list = new ArrayList<>();
		SubjectEntity s1 = new SubjectEntity();
		s1.setIdentificationNumber("11111111");
		s1.setId(1);
		
		List<InvestigationEntity> investigations = new ArrayList<>();
		InvestigationEntity i1 = new InvestigationEntity();
		i1.setNumberInvestigation(1);
		i1.setCompleted(true);
		InvestigationEntity i2 = new InvestigationEntity();
		i2.setNumberInvestigation(2);
		i2.setCompleted(false);

		investigations.add(i1);
		investigations.add(i2);
		
		s1.setInvestigations(investigations);
		list.add(s1);

		when(this.researcherBusiness.getAllSubjectsAndInvestigationsByResearcher(any())).thenReturn(list);
		ResponseEntity<?> response = this.researcherControllerImpl.getSubjectsAndInvestigationsFromIdResearcher(id);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, ((SubjectListFromResearcherDto)response.getBody()).getList().size());
		
		assertTrue(((SubjectListFromResearcherDto)response.getBody()).getList().get(0).getFirstInvestigationCompleted());
		assertFalse(((SubjectListFromResearcherDto)response.getBody()).getList().get(0).getSecondInvestigationCompleted());

		assertEquals(s1.getId(), ((SubjectListFromResearcherDto)response.getBody()).getList().get(0).getId());
		assertEquals(s1.getIdentificationNumber(), ((SubjectListFromResearcherDto)response.getBody()).getList().get(0).getIdentificationNumber());
		
		verify(this.researcherBusiness, times(1)).getAllSubjectsAndInvestigationsByResearcher(any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getSubjectsAndInvestigationsFromIdResearcherNotEmptyUnorderedInvestigationsListTest() {
		String id = "24";
		List<SubjectEntity> list = new ArrayList<>();
		SubjectEntity s1 = new SubjectEntity();
		s1.setIdentificationNumber("11111111");
		s1.setId(1);
		
		List<InvestigationEntity> investigations = new ArrayList<>();
		InvestigationEntity i1 = new InvestigationEntity();
		i1.setNumberInvestigation(2);
		i1.setCompleted(true);
		InvestigationEntity i2 = new InvestigationEntity();
		i2.setNumberInvestigation(1);
		i2.setCompleted(false);

		investigations.add(i1);
		investigations.add(i2);
		
		s1.setInvestigations(investigations);
		list.add(s1);

		when(this.researcherBusiness.getAllSubjectsAndInvestigationsByResearcher(any())).thenReturn(list);
		ResponseEntity<?> response = this.researcherControllerImpl.getSubjectsAndInvestigationsFromIdResearcher(id);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, ((SubjectListFromResearcherDto)response.getBody()).getList().size());
		
		assertFalse(((SubjectListFromResearcherDto)response.getBody()).getList().get(0).getFirstInvestigationCompleted());
		assertTrue(((SubjectListFromResearcherDto)response.getBody()).getList().get(0).getSecondInvestigationCompleted());

		assertEquals(s1.getId(), ((SubjectListFromResearcherDto)response.getBody()).getList().get(0).getId());
		assertEquals(s1.getIdentificationNumber(), ((SubjectListFromResearcherDto)response.getBody()).getList().get(0).getIdentificationNumber());
		
		verify(this.researcherBusiness, times(1)).getAllSubjectsAndInvestigationsByResearcher(any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	
	@Test
	public void registerSubjectExceptionTest() {
		SubjectInfoDto subject = new SubjectInfoDto();
		
		when(this.subjectBusiness.getSubjectFromIdentificationNumber(any())).thenThrow(new IllegalArgumentException());
		ResponseEntity<?> response = this.researcherControllerImpl.registerSubject(subject);
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.subjectBusiness, times(1)).getSubjectFromIdentificationNumber(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void registerSubjectSubjectNullTest() {
		SubjectInfoDto subject = new SubjectInfoDto();
		SubjectEntity subjectFound = new SubjectEntity();
		
		when(this.subjectBusiness.getSubjectFromIdentificationNumber(any())).thenReturn(subjectFound);
		ResponseEntity<?> response = this.researcherControllerImpl.registerSubject(subject);
		
		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
		
		verify(this.subjectBusiness, times(1)).getSubjectFromIdentificationNumber(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void registerSubjectUserExceptionTest() {
		SubjectInfoDto subject = new SubjectInfoDto();
		subject.setIdentificationNumber("11111111");
		subject.setUsernameResearcher("12345678A");
		SubjectEntity subjectFound = null;
		
		when(this.subjectBusiness.getSubjectFromIdentificationNumber(any())).thenReturn(subjectFound);
		when(this.userBusiness.findByUsername(any())).thenThrow(new IllegalArgumentException());

		ResponseEntity<?> response = this.researcherControllerImpl.registerSubject(subject);
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.subjectBusiness, times(1)).getSubjectFromIdentificationNumber(any());
		verifyZeroInteractions(this.researcherBusiness);
		verify(this.userBusiness, times(1)).findByUsername(any());
	}
	
	@Test
	public void registerSubjectUserNullTest() {
		SubjectInfoDto subject = new SubjectInfoDto();
		subject.setIdentificationNumber("11111111");
		subject.setUsernameResearcher("12345678A");
		SubjectEntity subjectFound = null;
		UserEntity user = null;
		
		when(this.subjectBusiness.getSubjectFromIdentificationNumber(any())).thenReturn(subjectFound);
		when(this.userBusiness.findByUsername(any())).thenReturn(user);

		ResponseEntity<?> response = this.researcherControllerImpl.registerSubject(subject);
		
		assertEquals(HttpStatus.GONE, response.getStatusCode());
		
		verify(this.subjectBusiness, times(1)).getSubjectFromIdentificationNumber(any());
		verifyZeroInteractions(this.researcherBusiness);
		verify(this.userBusiness, times(1)).findByUsername(any());
	}
	
	@Test
	public void registerSubjectSaveExceptionTest() {
		SubjectInfoDto subject = new SubjectInfoDto();
		subject.setIdentificationNumber("11111111");
		subject.setUsernameResearcher("12345678A");
		SubjectEntity subjectFound = null;
		UserEntity user = new UserEntity();
		
		when(this.subjectBusiness.getSubjectFromIdentificationNumber(any())).thenReturn(subjectFound);
		when(this.userBusiness.findByUsername(any())).thenReturn(user);
		when(this.subjectBusiness.saveSubject(any())).thenThrow(new IllegalArgumentException());

		ResponseEntity<?> response = this.researcherControllerImpl.registerSubject(subject);
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.subjectBusiness, times(1)).getSubjectFromIdentificationNumber(any());
		verify(this.subjectBusiness, times(1)).saveSubject(any());

		verifyZeroInteractions(this.researcherBusiness);
		verify(this.userBusiness, times(1)).findByUsername(any());
	}
	
	@Test
	public void registerSubjectSaveNullTest() {
		SubjectInfoDto subject = new SubjectInfoDto();
		subject.setIdentificationNumber("11111111");
		subject.setUsernameResearcher("12345678A");
		SubjectEntity subjectFound = null;
		UserEntity user = new UserEntity();
		SubjectEntity subjectSaved = null;

		
		when(this.subjectBusiness.getSubjectFromIdentificationNumber(any())).thenReturn(subjectFound);
		when(this.userBusiness.findByUsername(any())).thenReturn(user);
		when(this.subjectBusiness.saveSubject(any())).thenReturn(subjectSaved);

		ResponseEntity<?> response = this.researcherControllerImpl.registerSubject(subject);
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.subjectBusiness, times(1)).getSubjectFromIdentificationNumber(any());
		verify(this.subjectBusiness, times(1)).saveSubject(any());
		verifyZeroInteractions(this.researcherBusiness);
		verify(this.userBusiness, times(1)).findByUsername(any());
	}
	
	@Test
	public void registerSubjectOKTest() {
		SubjectInfoDto subject = new SubjectInfoDto();
		subject.setIdentificationNumber("11111111");
		subject.setUsernameResearcher("12345678A");
		SubjectEntity subjectFound = null;
		UserEntity user = new UserEntity();
		SubjectEntity subjectSaved = new SubjectEntity();
		
		subjectSaved.setIdentificationNumber("11111111");
		UserEntity user2 = new UserEntity();
		user2.setUsername("12345678A");
		subjectSaved.setIdResearcher(user2);

		when(this.subjectBusiness.getSubjectFromIdentificationNumber(any())).thenReturn(subjectFound);
		when(this.userBusiness.findByUsername(any())).thenReturn(user);
		when(this.subjectBusiness.saveSubject(any())).thenReturn(subjectSaved);

		ResponseEntity<?> response = this.researcherControllerImpl.registerSubject(subject);
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		
		assertEquals(subject.getIdentificationNumber(), ((SubjectInfoDto)response.getBody()).getIdentificationNumber());
		assertEquals(subject.getUsernameResearcher(), ((SubjectInfoDto)response.getBody()).getUsernameResearcher());

		verify(this.subjectBusiness, times(1)).getSubjectFromIdentificationNumber(any());
		verify(this.subjectBusiness, times(1)).saveSubject(any());
		verifyZeroInteractions(this.researcherBusiness);
		verify(this.userBusiness, times(1)).findByUsername(any());
	}
	
	
	@Test
	public void getNumberInvestigationsCompletedFromSubjectResearcherSubjectExceptionTest() {
		String identificationNumber = "11111111";
		
		when(this.subjectBusiness.getNumberInvestigationsCompletedFromSubject(any())).thenThrow(new IllegalArgumentException());
		ResponseEntity<?> response = this.researcherControllerImpl.getNumberInvestigationsCompletedFromSubjectResearcher(identificationNumber);
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.subjectBusiness, times(1)).getNumberInvestigationsCompletedFromSubject(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	
	@Test
	public void getNumberInvestigationsCompletedFromSubjectResearcher_Test() {
		String identificationNumber = "11111111";
		
		when(this.subjectBusiness.getNumberInvestigationsCompletedFromSubject(any())).thenReturn(2);
		ResponseEntity<?> response = this.researcherControllerImpl.getNumberInvestigationsCompletedFromSubjectResearcher(identificationNumber);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(2, ((NumberInvestigationsCompletedSubjectDto)response.getBody()).getNumberInvestigationsCompleted().longValue());

		verify(this.subjectBusiness, times(1)).getNumberInvestigationsCompletedFromSubject(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	
	@Test
	public void registerInvestigationDetailsExceptionTest() {
		InvestigationDetailsToRegisterDto investigationDetailsToRegisterDto = new InvestigationDetailsToRegisterDto();
		when(this.researcherBusiness.getInvestigationBySubjectAndNumberInvestigation(any(), any())).thenThrow(new IllegalArgumentException());
		ResponseEntity<?> response = this.researcherControllerImpl.registerInvestigationDetails(investigationDetailsToRegisterDto);
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.researcherBusiness, times(1)).getInvestigationBySubjectAndNumberInvestigation(any(), any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void registerInvestigationDetailsInvestigationNullTest() {
		InvestigationEntity investigationEntityToSave = null;
		InvestigationDetailsToRegisterDto investigationDetailsToRegisterDto = new InvestigationDetailsToRegisterDto();
		when(this.researcherBusiness.getInvestigationBySubjectAndNumberInvestigation(any(), any())).thenReturn(investigationEntityToSave);
		ResponseEntity<?> response = this.researcherControllerImpl.registerInvestigationDetails(investigationDetailsToRegisterDto);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		
		verify(this.researcherBusiness, times(1)).getInvestigationBySubjectAndNumberInvestigation(any(), any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void registerInvestigationDetailsInvestigationCompletedTest() {
		InvestigationEntity investigationEntityToSave = new InvestigationEntity();
		investigationEntityToSave.setCompleted(true);
		InvestigationDetailsToRegisterDto investigationDetailsToRegisterDto = new InvestigationDetailsToRegisterDto();
		when(this.researcherBusiness.getInvestigationBySubjectAndNumberInvestigation(any(), any())).thenReturn(investigationEntityToSave);
		ResponseEntity<?> response = this.researcherControllerImpl.registerInvestigationDetails(investigationDetailsToRegisterDto);
		
		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
		
		verify(this.researcherBusiness, times(1)).getInvestigationBySubjectAndNumberInvestigation(any(), any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void registerInvestigationDetailsInvestigationDetailsTest() {
		InvestigationEntity investigationEntityToSave = new InvestigationEntity();
		investigationEntityToSave.setCompleted(false);
		investigationEntityToSave.setInvestigationEntityDetails(new InvestigationEntityDetails());

		InvestigationDetailsToRegisterDto investigationDetailsToRegisterDto = new InvestigationDetailsToRegisterDto();
		when(this.researcherBusiness.getInvestigationBySubjectAndNumberInvestigation(any(), any())).thenReturn(investigationEntityToSave);
		ResponseEntity<?> response = this.researcherControllerImpl.registerInvestigationDetails(investigationDetailsToRegisterDto);
		
		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
		
		verify(this.researcherBusiness, times(1)).getInvestigationBySubjectAndNumberInvestigation(any(), any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void registerInvestigationDetailsInvestigationSaveExceptionTest() {
		InvestigationEntity investigationEntityToSave = new InvestigationEntity();
		investigationEntityToSave.setCompleted(false);
		
		InvestigationDetailsToRegisterDto investigationDetailsToRegisterDto = new InvestigationDetailsToRegisterDto();
		when(this.researcherBusiness.getInvestigationBySubjectAndNumberInvestigation(any(), any())).thenReturn(investigationEntityToSave);
		when(this.researcherBusiness.saveInvestigationDetails(any())).thenThrow(new IllegalArgumentException());
		ResponseEntity<?> response = this.researcherControllerImpl.registerInvestigationDetails(investigationDetailsToRegisterDto);
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.researcherBusiness, times(1)).getInvestigationBySubjectAndNumberInvestigation(any(), any());
		verify(this.researcherBusiness, times(1)).saveInvestigationDetails(any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void registerInvestigationDetailsInvestigationSaveFailTest() {
		InvestigationEntity investigationEntityToSave = new InvestigationEntity();
		investigationEntityToSave.setCompleted(false);
		
		InvestigationDetailsToRegisterDto investigationDetailsToRegisterDto = new InvestigationDetailsToRegisterDto();
		when(this.researcherBusiness.getInvestigationBySubjectAndNumberInvestigation(any(), any())).thenReturn(investigationEntityToSave);
		when(this.researcherBusiness.saveInvestigationDetails(any())).thenReturn(false);
		ResponseEntity<?> response = this.researcherControllerImpl.registerInvestigationDetails(investigationDetailsToRegisterDto);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		
		verify(this.researcherBusiness, times(1)).getInvestigationBySubjectAndNumberInvestigation(any(), any());
		verify(this.researcherBusiness, times(1)).saveInvestigationDetails(any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void registerInvestigationDetailsInvestigationOKTest() {
		InvestigationEntity investigationEntityToSave = new InvestigationEntity();
		investigationEntityToSave.setCompleted(false);
		
		InvestigationDetailsToRegisterDto investigationDetailsToRegisterDto = new InvestigationDetailsToRegisterDto();
		when(this.researcherBusiness.getInvestigationBySubjectAndNumberInvestigation(any(), any())).thenReturn(investigationEntityToSave);
		when(this.researcherBusiness.saveInvestigationDetails(any())).thenReturn(true);
		ResponseEntity<?> response = this.researcherControllerImpl.registerInvestigationDetails(investigationDetailsToRegisterDto);
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		
		verify(this.researcherBusiness, times(1)).getInvestigationBySubjectAndNumberInvestigation(any(), any());
		verify(this.researcherBusiness, times(1)).saveInvestigationDetails(any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	
	@Test
	public void getInvestigationDetailsInvalidIDTest() {
		String idSubject = "INVALID_ID";
		String numberInvestigation = "2";
		
		ResponseEntity<?> response = this.researcherControllerImpl.getInvestigationDetails(idSubject, numberInvestigation);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getInvestigationDetailsInvalidNumberInvestigationTest() {
		String idSubject = "2";
		String numberInvestigation = "INVALID_NI";
		
		ResponseEntity<?> response = this.researcherControllerImpl.getInvestigationDetails(idSubject, numberInvestigation);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getInvestigationDetailsGetExceptionTest() {
		String idSubject = "1";
		String numberInvestigation = "2";
		
		when(this.researcherBusiness.getInvestigationBySubjectAndNumberInvestigation(any(), any())).thenThrow(new IllegalArgumentException());
		
		ResponseEntity<?> response = this.researcherControllerImpl.getInvestigationDetails(idSubject, numberInvestigation);
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.researcherBusiness, times(1)).getInvestigationBySubjectAndNumberInvestigation(any(), any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getInvestigationDetailsInvestigationNullTest() {
		String idSubject = "1";
		String numberInvestigation = "2";
		InvestigationEntity investigationEntity = null;
		
		when(this.researcherBusiness.getInvestigationBySubjectAndNumberInvestigation(any(), any())).thenReturn(investigationEntity);
		ResponseEntity<?> response = this.researcherControllerImpl.getInvestigationDetails(idSubject, numberInvestigation);
		
		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
		
		verify(this.researcherBusiness, times(1)).getInvestigationBySubjectAndNumberInvestigation(any(), any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getInvestigationDetailsInvestigationNotCompletedTest() {
		String idSubject = "1";
		String numberInvestigation = "2";
		InvestigationEntity investigationEntity = new InvestigationEntity();
		investigationEntity.setCompleted(false);
		
		when(this.researcherBusiness.getInvestigationBySubjectAndNumberInvestigation(any(), any())).thenReturn(investigationEntity);
		ResponseEntity<?> response = this.researcherControllerImpl.getInvestigationDetails(idSubject, numberInvestigation);
		
		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
		
		verify(this.researcherBusiness, times(1)).getInvestigationBySubjectAndNumberInvestigation(any(), any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getInvestigationDetailsInvestigationDetailsNullTest() {
		String idSubject = "1";
		String numberInvestigation = "2";
		InvestigationEntity investigationEntity = new InvestigationEntity();
		investigationEntity.setCompleted(true);
		
		when(this.researcherBusiness.getInvestigationBySubjectAndNumberInvestigation(any(), any())).thenReturn(investigationEntity);
		ResponseEntity<?> response = this.researcherControllerImpl.getInvestigationDetails(idSubject, numberInvestigation);
		
		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
		
		verify(this.researcherBusiness, times(1)).getInvestigationBySubjectAndNumberInvestigation(any(), any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getInvestigationDetailsInvestigationOKTest() {
		String idSubject = "1";
		String numberInvestigation = "2";
		InvestigationEntity investigationEntity = new InvestigationEntity();
		
		SubjectEntity subjectEntity = new SubjectEntity();
		subjectEntity.setIdentificationNumber("11111111");
		investigationEntity.setSubject(subjectEntity);
		
		investigationEntity.setCompleted(true);
		investigationEntity.setInvestigationEntityDetails(new InvestigationEntityDetails());
		
		when(this.researcherBusiness.getInvestigationBySubjectAndNumberInvestigation(any(), any())).thenReturn(investigationEntity);
		ResponseEntity<?> response = this.researcherControllerImpl.getInvestigationDetails(idSubject, numberInvestigation);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(subjectEntity.getIdentificationNumber(), ((InvestigationDetailsToShowDto)response.getBody()).getIdentificationNumber());

		
		verify(this.researcherBusiness, times(1)).getInvestigationBySubjectAndNumberInvestigation(any(), any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	
	@Test
	public void getAllInvestigationDetailsInvalidIDTest() {
		String idSubject = "INVALID_ID";
		ResponseEntity<?> response = this.researcherControllerImpl.getAllInvestigationDetails(idSubject);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getAllInvestigationDetailsGetAllExceptionTest() {
		String idSubject = "2";

		when(this.researcherBusiness.getAllSubjectsByResearcher(any())).thenThrow(new IllegalArgumentException());
		ResponseEntity<?> response = this.researcherControllerImpl.getAllInvestigationDetails(idSubject);
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

		verify(this.researcherBusiness, times(1)).getAllSubjectsByResearcher(any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getAllInvestigationDetailsListNullTest() {
		String idSubject = "2";
		List<SubjectEntity> listSubjects = null;

		when(this.researcherBusiness.getAllSubjectsByResearcher(any())).thenReturn(listSubjects);
		ResponseEntity<?> response = this.researcherControllerImpl.getAllInvestigationDetails(idSubject);
		
		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());

		verify(this.researcherBusiness, times(1)).getAllSubjectsByResearcher(any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getAllInvestigationDetailsEmptyListTest() {
		String idSubject = "2";
		List<SubjectEntity> listSubjects = new ArrayList<>();

		when(this.researcherBusiness.getAllSubjectsByResearcher(any())).thenReturn(listSubjects);
		ResponseEntity<?> response = this.researcherControllerImpl.getAllInvestigationDetails(idSubject);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(0, ((InvestigationDetailsToShowListDto)response.getBody()).getList().size());

		verify(this.researcherBusiness, times(1)).getAllSubjectsByResearcher(any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getAllInvestigationDetailsNotEmptyListTest() {
		String idSubject = "2";
		List<SubjectEntity> listSubjects = new ArrayList<>();
		SubjectEntity s1 = new SubjectEntity();
		s1.setIdentificationNumber("11111111");
		
		List<InvestigationEntity> listInvestigations = new ArrayList<>();
		InvestigationEntity i1 = new InvestigationEntity();
		i1.setCompleted(true);
		i1.setInvestigationEntityDetails(new InvestigationEntityDetails());
		InvestigationEntity i2 = new InvestigationEntity();
		i2.setCompleted(false);
		i2.setInvestigationEntityDetails(new InvestigationEntityDetails());

		listInvestigations.add(i1);
		listInvestigations.add(i2);
		s1.setInvestigations(listInvestigations);
		
		listSubjects.add(s1);
		
		when(this.researcherBusiness.getAllSubjectsByResearcher(any())).thenReturn(listSubjects);
		ResponseEntity<?> response = this.researcherControllerImpl.getAllInvestigationDetails(idSubject);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(2, ((InvestigationDetailsToShowListDto)response.getBody()).getList().size());
		assertEquals(s1.getIdentificationNumber(), ((InvestigationDetailsToShowListDto)response.getBody()).getList().get(0).getIdentificationNumber());
		assertEquals(s1.getIdentificationNumber(), ((InvestigationDetailsToShowListDto)response.getBody()).getList().get(1).getIdentificationNumber());

		verify(this.researcherBusiness, times(1)).getAllSubjectsByResearcher(any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
}
