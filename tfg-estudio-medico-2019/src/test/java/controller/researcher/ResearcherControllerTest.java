package controller.researcher;

import java.util.ArrayList;
import java.util.IllegalFormatException;
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
import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
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
		s1.setIdentificationNumber(11111111);
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
		s1.setIdentificationNumber(11111111);
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
		subject.setIdentificationNumber(11111111);
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
		subject.setIdentificationNumber(11111111);
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
		subject.setIdentificationNumber(11111111);
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
		subject.setIdentificationNumber(11111111);
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
		subject.setIdentificationNumber(11111111);
		subject.setUsernameResearcher("12345678A");
		SubjectEntity subjectFound = null;
		UserEntity user = new UserEntity();
		SubjectEntity subjectSaved = new SubjectEntity();
		
		subjectSaved.setIdentificationNumber(11111111);
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
	public void getNumberInvestigationsCompletedFromSubjectResearcherInvalidIdentificationNumberTest() {
		String identificationNumber = "INVALID_ID_NUMBER";
		
		ResponseEntity<?> response = this.researcherControllerImpl.getNumberInvestigationsCompletedFromSubjectResearcher(identificationNumber);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		
		verifyZeroInteractions(this.subjectBusiness);
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
	
}
