package controller.admin;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.example.tfgestudiomedico2019.business.researcher.ResearcherBusiness;
import com.example.tfgestudiomedico2019.business.subject.SubjectBusiness;
import com.example.tfgestudiomedico2019.business.user.UserBusiness;
import com.example.tfgestudiomedico2019.controller.admin.AdminControllerImpl;
import com.example.tfgestudiomedico2019.model.domain.Role;
import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.model.rest.investigation.NumberInvestigationsCompletedSubjectDto;
import com.example.tfgestudiomedico2019.model.rest.subject.SubjectInfoListDto;
import com.example.tfgestudiomedico2019.model.rest.subject.SubjectToDeleteDto;
import com.example.tfgestudiomedico2019.model.rest.user.UserDto;
import com.example.tfgestudiomedico2019.model.rest.user.UserListDto;
import com.example.tfgestudiomedico2019.model.rest.user.UserToDeleteDto;
import com.example.tfgestudiomedico2019.model.rest.user.UserToRegisterDto;

@RunWith(MockitoJUnitRunner.class)
public class AdminControllerTest {

	
	@InjectMocks
	private AdminControllerImpl adminControllerImpl;
	
	@Mock
	private ResearcherBusiness researcherBusiness;
	
	@Mock
	private SubjectBusiness subjectBusiness;
	
	@Mock
	private UserBusiness userBusiness;
	
	
	@Test
	public void getAllUsersExceptionTest() {
		when(this.userBusiness.getAllResearchers()).thenThrow(new IllegalArgumentException());
		ResponseEntity<?> response = this.adminControllerImpl.getAllUsers();
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.userBusiness, times(1)).getAllResearchers();
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.researcherBusiness);
	}
	
	@Test
	public void getAllUsersEmptyListTest() {
		List<UserEntity> listResearchers = new ArrayList<>();
		when(this.userBusiness.getAllResearchers()).thenReturn(listResearchers);
		ResponseEntity<?> response = this.adminControllerImpl.getAllUsers();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(0, ((UserListDto)response.getBody()).getList().size());
		
		verify(this.userBusiness, times(1)).getAllResearchers();
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.researcherBusiness);
	}
	
	@Test
	public void getAllUsersNotEmptyListTest() {
		List<UserEntity> listResearchers = new ArrayList<>();
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername("12345678A");
		userEntity.setName("LUIS");
		userEntity.setSurname("GARCIA");
		userEntity.setGender("hombre");
		userEntity.setId(2);
		
		listResearchers.add(userEntity);
		
		when(this.userBusiness.getAllResearchers()).thenReturn(listResearchers);
		ResponseEntity<?> response = this.adminControllerImpl.getAllUsers();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, ((UserListDto)response.getBody()).getList().size());
		
		assertEquals(userEntity.getUsername(), ((UserListDto)response.getBody()).getList().get(0).getUsername());
		assertEquals(userEntity.getName(), ((UserListDto)response.getBody()).getList().get(0).getName());
		assertEquals(userEntity.getSurname(), ((UserListDto)response.getBody()).getList().get(0).getSurname());
		assertEquals(userEntity.getGender(), ((UserListDto)response.getBody()).getList().get(0).getGender());
		assertEquals(userEntity.getId(), ((UserListDto)response.getBody()).getList().get(0).getId());
		
		verify(this.userBusiness, times(1)).getAllResearchers();
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.researcherBusiness);
	}
	
	
	@Test
	public void deleteResearcherExceptionTest() {
		UserToDeleteDto userToDeleteDto = new UserToDeleteDto();
		userToDeleteDto.setUsername("12345678A");
		
		when(this.userBusiness.findByUsername(any())).thenThrow(new IllegalArgumentException());
		ResponseEntity<?> response = this.adminControllerImpl.deleteResearcher(userToDeleteDto);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.userBusiness, times(1)).findByUsername(any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.researcherBusiness);
	}
	
	@Test
	public void deleteResearcherUserToDeleteNullTest() {
		UserToDeleteDto userToDeleteDto = new UserToDeleteDto();
		userToDeleteDto.setUsername("12345678A");
		
		UserEntity userToDelete = null;
		
		when(this.userBusiness.findByUsername(any())).thenReturn(userToDelete);
		ResponseEntity<?> response = this.adminControllerImpl.deleteResearcher(userToDeleteDto);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		
		verify(this.userBusiness, times(1)).findByUsername(any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.researcherBusiness);
	}
	
	@Test
	public void deleteResearcherUserToDeleteAdminTest() {
		UserToDeleteDto userToDeleteDto = new UserToDeleteDto();
		userToDeleteDto.setUsername("12345678A");
		
		UserEntity userToDelete = new UserEntity();
		userToDelete.setRole(Role.ADMIN.name());
		
		when(this.userBusiness.findByUsername(any())).thenReturn(userToDelete);
		ResponseEntity<?> response = this.adminControllerImpl.deleteResearcher(userToDeleteDto);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		
		verify(this.userBusiness, times(1)).findByUsername(any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.researcherBusiness);
	}
	
	@Test
	public void deleteResearcherUserToDeleteHasSubjectsTest() {
		UserToDeleteDto userToDeleteDto = new UserToDeleteDto();
		userToDeleteDto.setUsername("12345678A");
		
		UserEntity userToDelete = new UserEntity();
		userToDelete.setRole(Role.RESEARCHER.name());
		
		List<SubjectEntity> subjects = new ArrayList<>();
		subjects.add(new SubjectEntity());
		userToDelete.setSubjects(subjects);
		
		when(this.userBusiness.findByUsername(any())).thenReturn(userToDelete);
		ResponseEntity<?> response = this.adminControllerImpl.deleteResearcher(userToDeleteDto);
		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
		
		verify(this.userBusiness, times(1)).findByUsername(any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.researcherBusiness);
	}
	
	@Test
	public void deleteResearcherDeleteExceptionTest() {
		UserToDeleteDto userToDeleteDto = new UserToDeleteDto();
		userToDeleteDto.setUsername("12345678A");
		
		UserEntity userToDelete = new UserEntity();
		userToDelete.setRole(Role.RESEARCHER.name());
		
		List<SubjectEntity> subjects = new ArrayList<>();
		userToDelete.setSubjects(subjects);
		
		
		when(this.userBusiness.deleteResearcher(any())).thenThrow(new IllegalArgumentException());
		when(this.userBusiness.findByUsername(any())).thenReturn(userToDelete);
		ResponseEntity<?> response = this.adminControllerImpl.deleteResearcher(userToDeleteDto);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.userBusiness, times(1)).deleteResearcher(any());
		verify(this.userBusiness, times(1)).findByUsername(any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.researcherBusiness);
	}
	
	@Test
	public void deleteResearcherDeleteFailTest() {
		UserToDeleteDto userToDeleteDto = new UserToDeleteDto();
		userToDeleteDto.setUsername("12345678A");
		
		UserEntity userToDelete = new UserEntity();
		userToDelete.setRole(Role.RESEARCHER.name());
		
		List<SubjectEntity> subjects = new ArrayList<>();
		userToDelete.setSubjects(subjects);
		
		
		when(this.userBusiness.deleteResearcher(any())).thenReturn(false);
		when(this.userBusiness.findByUsername(any())).thenReturn(userToDelete);
		ResponseEntity<?> response = this.adminControllerImpl.deleteResearcher(userToDeleteDto);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		
		verify(this.userBusiness, times(1)).deleteResearcher(any());
		verify(this.userBusiness, times(1)).findByUsername(any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.researcherBusiness);
	}
	
	@Test
	public void deleteResearcherOKTest() {
		UserToDeleteDto userToDeleteDto = new UserToDeleteDto();
		userToDeleteDto.setUsername("12345678A");
		
		UserEntity userToDelete = new UserEntity();
		userToDelete.setRole(Role.RESEARCHER.name());
		
		List<SubjectEntity> subjects = new ArrayList<>();
		userToDelete.setSubjects(subjects);
		
		
		when(this.userBusiness.deleteResearcher(any())).thenReturn(true);
		when(this.userBusiness.findByUsername(any())).thenReturn(userToDelete);
		ResponseEntity<?> response = this.adminControllerImpl.deleteResearcher(userToDeleteDto);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		verify(this.userBusiness, times(1)).deleteResearcher(any());
		verify(this.userBusiness, times(1)).findByUsername(any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.researcherBusiness);
	}
	
	
	@Test
	public void registerResearcherExceptionTest() {
		UserToRegisterDto userToRegisterDto = new UserToRegisterDto();
		userToRegisterDto.setUsername("12345678A");
		
		when(this.userBusiness.findByUsername(any())).thenThrow(new IllegalArgumentException());
		ResponseEntity<?> response = this.adminControllerImpl.registerResearcher(userToRegisterDto);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.userBusiness, times(1)).findByUsername(any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.researcherBusiness);
	}
	
	@Test
	public void registerResearcherUserAlreadyExistsTest() {
		UserToRegisterDto userToRegisterDto = new UserToRegisterDto();
		userToRegisterDto.setUsername("12345678A");
		UserEntity userFound = new UserEntity();
		
		when(this.userBusiness.findByUsername(any())).thenReturn(userFound);
		ResponseEntity<?> response = this.adminControllerImpl.registerResearcher(userToRegisterDto);
		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
		
		verify(this.userBusiness, times(1)).findByUsername(any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.researcherBusiness);
	}
	
	@Test
	public void registerResearcherSaveExceptionTest() {
		UserToRegisterDto userToRegisterDto = new UserToRegisterDto();
		userToRegisterDto.setUsername("12345678A");
		UserEntity userFound = null;
		
		
		when(this.userBusiness.saveUser(any())).thenThrow(new IllegalArgumentException());
		when(this.userBusiness.findByUsername(any())).thenReturn(userFound);
		ResponseEntity<?> response = this.adminControllerImpl.registerResearcher(userToRegisterDto);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.userBusiness, times(1)).saveUser(any());
		verify(this.userBusiness, times(1)).findByUsername(any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.researcherBusiness);
	}
	
	@Test
	public void registerResearcherSaveFailTest() {
		UserToRegisterDto userToRegisterDto = new UserToRegisterDto();
		userToRegisterDto.setUsername("12345678A");
		UserEntity userFound = null;
		UserEntity userSaved = null;
		
		
		when(this.userBusiness.saveUser(any())).thenReturn(userSaved);
		when(this.userBusiness.findByUsername(any())).thenReturn(userFound);
		ResponseEntity<?> response = this.adminControllerImpl.registerResearcher(userToRegisterDto);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.userBusiness, times(1)).saveUser(any());
		verify(this.userBusiness, times(1)).findByUsername(any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.researcherBusiness);
	}
	
	@Test
	public void registerResearcherOKTest() {
		UserToRegisterDto userToRegisterDto = new UserToRegisterDto();
		userToRegisterDto.setUsername("12345678A");
		UserEntity userFound = null;
		
		UserEntity userSaved = new UserEntity();
		userSaved.setUsername("12345678A");
		userSaved.setName("LUIS");
		userSaved.setSurname("GARCIA");
		userSaved.setGender("hombre");
		userSaved.setId(2);
		
		
		when(this.userBusiness.saveUser(any())).thenReturn(userSaved);
		when(this.userBusiness.findByUsername(any())).thenReturn(userFound);
		ResponseEntity<?> response = this.adminControllerImpl.registerResearcher(userToRegisterDto);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		
		assertEquals(userSaved.getUsername(), ((UserDto)response.getBody()).getUsername());
		assertEquals(userSaved.getName(), ((UserDto)response.getBody()).getName());
		assertEquals(userSaved.getSurname(), ((UserDto)response.getBody()).getSurname());
		assertEquals(userSaved.getGender(), ((UserDto)response.getBody()).getGender());
		assertEquals(userSaved.getId(), ((UserDto)response.getBody()).getId());
		
		verify(this.userBusiness, times(1)).saveUser(any());
		verify(this.userBusiness, times(1)).findByUsername(any());
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.researcherBusiness);
	}
	
	
	@Test
	public void getAllSubjectsExceptionTest() {
		when(this.researcherBusiness.getAllSubjects()).thenThrow(new IllegalArgumentException());
		ResponseEntity<?> response = this.adminControllerImpl.getAllSubjects();
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.researcherBusiness, times(1)).getAllSubjects();
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getAllSubjectsEmptyListTest() {
		List<SubjectEntity> entityList = new ArrayList<>();
		
		when(this.researcherBusiness.getAllSubjects()).thenReturn(entityList);
		ResponseEntity<?> response = this.adminControllerImpl.getAllSubjects();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(0, ((SubjectInfoListDto)response.getBody()).getList().size());
	
		verify(this.researcherBusiness, times(1)).getAllSubjects();
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getAllSubjectsNotEmptyListTest() {
		List<SubjectEntity> entityList = new ArrayList<>();
		SubjectEntity subjectEntity = new SubjectEntity();
		subjectEntity.setIdentificationNumber(11111111);
		UserEntity researcher = new UserEntity(); 
		researcher.setUsername("12345678A");
		subjectEntity.setIdResearcher(researcher);
		
		entityList.add(subjectEntity);
		
		when(this.researcherBusiness.getAllSubjects()).thenReturn(entityList);
		ResponseEntity<?> response = this.adminControllerImpl.getAllSubjects();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, ((SubjectInfoListDto)response.getBody()).getList().size());
		
		assertEquals(subjectEntity.getIdentificationNumber(), ((SubjectInfoListDto)response.getBody()).getList().get(0).getIdentificationNumber());
		assertEquals(subjectEntity.getIdResearcher().getUsername(), ((SubjectInfoListDto)response.getBody()).getList().get(0).getUsernameResearcher());
	
		verify(this.researcherBusiness, times(1)).getAllSubjects();
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	
	@Test
	public void deleteSubjectDtoNullTest() {
		SubjectToDeleteDto subjectToDeleteDto = null;
		
		ResponseEntity<?> response = this.adminControllerImpl.deleteSubject(subjectToDeleteDto);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void deleteSubjectExceptionTest() {
		SubjectToDeleteDto subjectToDeleteDto = new SubjectToDeleteDto();
		subjectToDeleteDto.setIdentificationNumber(11111111);
		
		when(this.subjectBusiness.deleteSubjectByIdentificationNumber(any())).thenThrow(new IllegalArgumentException());

		ResponseEntity<?> response = this.adminControllerImpl.deleteSubject(subjectToDeleteDto);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.subjectBusiness, times(1)).deleteSubjectByIdentificationNumber(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void deleteSubjectOKTest() {
		SubjectToDeleteDto subjectToDeleteDto = new SubjectToDeleteDto();
		subjectToDeleteDto.setIdentificationNumber(11111111);
		
		when(this.subjectBusiness.deleteSubjectByIdentificationNumber(any())).thenReturn(true);

		ResponseEntity<?> response = this.adminControllerImpl.deleteSubject(subjectToDeleteDto);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		verify(this.subjectBusiness, times(1)).deleteSubjectByIdentificationNumber(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void deleteSubjectFailTest() {
		SubjectToDeleteDto subjectToDeleteDto = new SubjectToDeleteDto();
		subjectToDeleteDto.setIdentificationNumber(11111111);
		
		when(this.subjectBusiness.deleteSubjectByIdentificationNumber(any())).thenReturn(false);

		ResponseEntity<?> response = this.adminControllerImpl.deleteSubject(subjectToDeleteDto);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		
		verify(this.subjectBusiness, times(1)).deleteSubjectByIdentificationNumber(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	
	@Test
	public void getNumberInvestigationsCompletedFromSubjectInvalidIdentificationNumberTest() {
		String identificationNumber = "INVALID_IDENTIFICATION_NUMBER";
		
		ResponseEntity<?> response = this.adminControllerImpl.getNumberInvestigationsCompletedFromSubject(identificationNumber);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getNumberInvestigationsCompletedFromSubjectExceptionTest() {
		String identificationNumber = "11111111";
		Integer numInvestigationsCompleted = 1;
		
		when(this.subjectBusiness.getNumberInvestigationsCompletedFromSubject(any())).thenReturn(numInvestigationsCompleted);
		ResponseEntity<?> response = this.adminControllerImpl.getNumberInvestigationsCompletedFromSubject(identificationNumber);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(numInvestigationsCompleted, ((NumberInvestigationsCompletedSubjectDto)response.getBody()).getNumberInvestigationsCompleted());

		verify(this.subjectBusiness, times(1)).getNumberInvestigationsCompletedFromSubject(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getNumberInvestigationsCompletedFromSubjectOKTest() {
		String identificationNumber = "11111111";
		
		when(this.subjectBusiness.getNumberInvestigationsCompletedFromSubject(any())).thenThrow(new IllegalArgumentException());
		ResponseEntity<?> response = this.adminControllerImpl.getNumberInvestigationsCompletedFromSubject(identificationNumber);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.subjectBusiness, times(1)).getNumberInvestigationsCompletedFromSubject(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
}
