package controller.admin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import com.example.tfgestudiomedico2019.model.entity.InvestigationEntity;
import com.example.tfgestudiomedico2019.model.entity.InvestigationEntityDetails;
import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.model.rest.investigation.InvestigationDetailsToShowDto;
import com.example.tfgestudiomedico2019.model.rest.investigation.InvestigationDetailsToShowListDto;
import com.example.tfgestudiomedico2019.model.rest.investigation.InvestigationDetailsToUpdateDto;
import com.example.tfgestudiomedico2019.model.rest.investigation.InvestigationToEditListDto;
import com.example.tfgestudiomedico2019.model.rest.investigation.NumberInvestigationsCompletedSubjectDto;
import com.example.tfgestudiomedico2019.model.rest.subject.SubjectInfoDto;
import com.example.tfgestudiomedico2019.model.rest.subject.SubjectInfoListDto;
import com.example.tfgestudiomedico2019.model.rest.subject.SubjectToDeleteDto;
import com.example.tfgestudiomedico2019.model.rest.user.UserDto;
import com.example.tfgestudiomedico2019.model.rest.user.UserListDto;
import com.example.tfgestudiomedico2019.model.rest.user.UserToDeleteDto;
import com.example.tfgestudiomedico2019.model.rest.user.UserToRegisterDto;
import com.example.tfgestudiomedico2019.model.rest.user.UserToUpdateDto;

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
	public void getAllUsersResearchersExceptionTest() {
		when(this.userBusiness.getAllResearchers()).thenThrow(new IllegalArgumentException());
		ResponseEntity<?> response = this.adminControllerImpl.getAllUsers();
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.userBusiness, times(1)).getAllResearchers();
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.researcherBusiness);
	}
	
	@Test
	public void getAllUsersAdminsExceptionTest() {
		when(this.userBusiness.getAllAdmins()).thenThrow(new IllegalArgumentException());
		ResponseEntity<?> response = this.adminControllerImpl.getAllUsers();
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.userBusiness, times(1)).getAllResearchers();
		verify(this.userBusiness, times(1)).getAllAdmins();

		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.researcherBusiness);
	}
	
	
	@Test
	public void getAllUsersEmptyListsTest() {
		List<UserEntity> listResearchers = new ArrayList<>();
		List<UserEntity> listAdmins = new ArrayList<>();

		
		when(this.userBusiness.getAllResearchers()).thenReturn(listResearchers);
		when(this.userBusiness.getAllAdmins()).thenReturn(listAdmins);

		ResponseEntity<?> response = this.adminControllerImpl.getAllUsers();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(0, ((UserListDto)response.getBody()).getListResearchers().size());
		assertEquals(0, ((UserListDto)response.getBody()).getListAdmins().size());

		
		verify(this.userBusiness, times(1)).getAllResearchers();
		verify(this.userBusiness, times(1)).getAllAdmins();
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.researcherBusiness);
	}
	

	@Test
	public void getAllUsersNotEmptyListTest() {
		List<UserEntity> listResearchers = new ArrayList<>();
		UserEntity researcher = new UserEntity();
		researcher.setUsername("12345678A");
		researcher.setName("LUIS");
		researcher.setSurname("GARCIA");
		researcher.setGender("hombre");
		researcher.setId(2);
		researcher.setRole(Role.RESEARCHER.name());
		
		listResearchers.add(researcher);
		
		List<UserEntity> listAdmins = new ArrayList<>();
		UserEntity admin = new UserEntity();
		admin.setUsername("12345678B");
		admin.setName("ASIER");
		admin.setSurname("ARÓSTEGUI PARADA");
		admin.setGender("hombre");
		admin.setId(3);
		admin.setRole(Role.ADMIN.name());
		
		listAdmins.add(admin);
	
		when(this.userBusiness.getAllResearchers()).thenReturn(listResearchers);
		when(this.userBusiness.getAllAdmins()).thenReturn(listAdmins);

		ResponseEntity<?> response = this.adminControllerImpl.getAllUsers();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, ((UserListDto)response.getBody()).getListResearchers().size());
		assertEquals(1, ((UserListDto)response.getBody()).getListAdmins().size());

		assertEquals(researcher.getUsername(), ((UserListDto)response.getBody()).getListResearchers().get(0).getUsername());
		assertEquals(researcher.getName(), ((UserListDto)response.getBody()).getListResearchers().get(0).getName());
		assertEquals(researcher.getSurname(), ((UserListDto)response.getBody()).getListResearchers().get(0).getSurname());
		assertEquals(researcher.getGender(), ((UserListDto)response.getBody()).getListResearchers().get(0).getGender());
		assertEquals(researcher.getId(), ((UserListDto)response.getBody()).getListResearchers().get(0).getId());
		
		assertEquals(admin.getUsername(), ((UserListDto)response.getBody()).getListAdmins().get(0).getUsername());
		assertEquals(admin.getName(), ((UserListDto)response.getBody()).getListAdmins().get(0).getName());
		assertEquals(admin.getSurname(), ((UserListDto)response.getBody()).getListAdmins().get(0).getSurname());
		assertEquals(admin.getGender(), ((UserListDto)response.getBody()).getListAdmins().get(0).getGender());
		assertEquals(admin.getId(), ((UserListDto)response.getBody()).getListAdmins().get(0).getId());
		
		verify(this.userBusiness, times(1)).getAllResearchers();
		verify(this.userBusiness, times(1)).getAllAdmins();
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
		subjectEntity.setIdentificationNumber("11111111");
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
		subjectToDeleteDto.setIdentificationNumber("11111111");
		
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
		subjectToDeleteDto.setIdentificationNumber("11111111");
		
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
		subjectToDeleteDto.setIdentificationNumber("11111111");
		
		when(this.subjectBusiness.deleteSubjectByIdentificationNumber(any())).thenReturn(false);

		ResponseEntity<?> response = this.adminControllerImpl.deleteSubject(subjectToDeleteDto);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		
		verify(this.subjectBusiness, times(1)).deleteSubjectByIdentificationNumber(any());
		verifyZeroInteractions(this.researcherBusiness);
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
	
	
	@Test
	public void getSubjectByIdentificationNumberExceptionTest() {
		String identificationNumber = "11111111";
		
		when(this.subjectBusiness.getSubjectFromIdentificationNumber(any())).thenThrow(new IllegalArgumentException());

		ResponseEntity<?> response = this.adminControllerImpl.getSubjectByIdentificationNumber(identificationNumber);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.subjectBusiness, times(1)).getSubjectFromIdentificationNumber(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getSubjectByIdentificationNumberNullTest() {
		String identificationNumber = "11111111";
		
		when(this.subjectBusiness.getSubjectFromIdentificationNumber(any())).thenReturn(null);

		ResponseEntity<?> response = this.adminControllerImpl.getSubjectByIdentificationNumber(identificationNumber);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		
		verify(this.subjectBusiness, times(1)).getSubjectFromIdentificationNumber(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getSubjectByIdentificationNumberOKTest() {
		String identificationNumber = "11111111";
		SubjectEntity entity = new SubjectEntity();
		entity.setIdentificationNumber(identificationNumber);
		
		when(this.subjectBusiness.getSubjectFromIdentificationNumber(any())).thenReturn(entity);

		ResponseEntity<?> response = this.adminControllerImpl.getSubjectByIdentificationNumber(identificationNumber);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(entity.getIdentificationNumber(), ((SubjectInfoDto)response.getBody()).getIdentificationNumber());

		verify(this.subjectBusiness, times(1)).getSubjectFromIdentificationNumber(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	
	@Test
	public void getSubjectsFromDNIResearcherExceptionTest() {
		String username = "INVALID_USERNAME";
		
		when(this.subjectBusiness.getSubjectsFromDNIResearcher(any())).thenThrow(new IllegalArgumentException());
		ResponseEntity<?> response = this.adminControllerImpl.getSubjectsFromDNIResearcher(username);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.subjectBusiness, times(1)).getSubjectsFromDNIResearcher(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getSubjectsFromDNIResearcherNotFoundTest() {
		String username = "INVALID_USERNAME";
		List<SubjectEntity> subjects = null;
		
		when(this.subjectBusiness.getSubjectsFromDNIResearcher(any())).thenReturn(subjects);
		ResponseEntity<?> response = this.adminControllerImpl.getSubjectsFromDNIResearcher(username);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		
		verify(this.subjectBusiness, times(1)).getSubjectsFromDNIResearcher(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getSubjectsFromDNIResearcherEmptyListTest() {
		String username = "12345678A";
		List<SubjectEntity> subjects = new ArrayList<>();
		
		when(this.subjectBusiness.getSubjectsFromDNIResearcher(any())).thenReturn(subjects);
		ResponseEntity<?> response = this.adminControllerImpl.getSubjectsFromDNIResearcher(username);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(0, ((SubjectInfoListDto)response.getBody()).getList().size());

		verify(this.subjectBusiness, times(1)).getSubjectsFromDNIResearcher(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getSubjectsFromDNIResearcherNotEmptysListTest() {
		String username = "12345678A";
		List<SubjectEntity> subjects = new ArrayList<>();
		SubjectEntity s1 = new SubjectEntity();
		SubjectEntity s2 = new SubjectEntity();
		subjects.add(s1);
		subjects.add(s2);

		when(this.subjectBusiness.getSubjectsFromDNIResearcher(any())).thenReturn(subjects);
		ResponseEntity<?> response = this.adminControllerImpl.getSubjectsFromDNIResearcher(username);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(2, ((SubjectInfoListDto)response.getBody()).getList().size());

		verify(this.subjectBusiness, times(1)).getSubjectsFromDNIResearcher(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	
	@Test
	public void getResearcherFromIdExceptionTest() {
		String id = "23";
		
		when(this.userBusiness.findById(any())).thenThrow(new IllegalArgumentException());
		ResponseEntity<?> response = this.adminControllerImpl.getResearcherFromId(id);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.userBusiness, times(1)).findById(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.subjectBusiness);
	}
	
	@Test
	public void getResearcherFromIdInvalidIdTest() {
		String id = "INVALID_ID";
		
		ResponseEntity<?> response = this.adminControllerImpl.getResearcherFromId(id);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verifyZeroInteractions(this.userBusiness);
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.subjectBusiness);
	}
	
	@Test
	public void getResearcherFromIdNullTest() {
		String id = "23";
		UserEntity entity = null;
		
		when(this.userBusiness.findById(any())).thenReturn(entity);
		ResponseEntity<?> response = this.adminControllerImpl.getResearcherFromId(id);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		
		verify(this.userBusiness, times(1)).findById(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.subjectBusiness);
	}
	
	@Test
	public void getResearcherFromIdAdminTest() {
		String id = "23";
		UserEntity entity = new UserEntity();
		entity.setRole(Role.ADMIN.name());
		
		when(this.userBusiness.findById(any())).thenReturn(entity);
		ResponseEntity<?> response = this.adminControllerImpl.getResearcherFromId(id);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		
		verify(this.userBusiness, times(1)).findById(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.subjectBusiness);
	}
	
	@Test
	public void getResearcherFromIdOKTest() {
		String id = "23";
		UserEntity entity = new UserEntity();
		entity.setRole(Role.RESEARCHER.name());
		entity.setUsername("12345678A");
		entity.setName("JUAN");
		entity.setSurname("GARCÍA");
		entity.setGender("hombre");
		entity.setId(23);

		when(this.userBusiness.findById(any())).thenReturn(entity);
		ResponseEntity<?> response = this.adminControllerImpl.getResearcherFromId(id);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(entity.getUsername(), ((UserDto)response.getBody()).getUsername());
		assertEquals(entity.getName(), ((UserDto)response.getBody()).getName());
		assertEquals(entity.getSurname(), ((UserDto)response.getBody()).getSurname());
		assertEquals(entity.getGender(), ((UserDto)response.getBody()).getGender());
		assertEquals(entity.getId(), ((UserDto)response.getBody()).getId());

		verify(this.userBusiness, times(1)).findById(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.subjectBusiness);
	}
	
	
	
	@Test
	public void updateResearcherInvalidIdTest() {
		UserToUpdateDto dto = new UserToUpdateDto();
		dto.setId("INVALID_ID");
		
		ResponseEntity<?> response = this.adminControllerImpl.updateResearcher(dto);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		
		verifyZeroInteractions(this.userBusiness);
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.subjectBusiness);
	}
	
	@Test
	public void updateResearcherExceptionTest() {
		UserToUpdateDto dto = new UserToUpdateDto();
		dto.setId("23");
		
		when(this.userBusiness.findById(any())).thenThrow(new IllegalArgumentException());
		ResponseEntity<?> response = this.adminControllerImpl.updateResearcher(dto);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.userBusiness, times(1)).findById(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.subjectBusiness);
	}
	
	@Test
	public void updateResearcherNullTest() {
		UserToUpdateDto dto = new UserToUpdateDto();
		dto.setId("23");
		UserEntity userToUpdate = null;
		
		when(this.userBusiness.findById(any())).thenReturn(userToUpdate);
		ResponseEntity<?> response = this.adminControllerImpl.updateResearcher(dto);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		
		verify(this.userBusiness, times(1)).findById(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.subjectBusiness);
	}
	
	@Test
	public void updateResearcherAdminTest() {
		UserToUpdateDto dto = new UserToUpdateDto();
		dto.setId("23");
		UserEntity userToUpdate = new UserEntity();
		userToUpdate.setRole(Role.ADMIN.name());
		
		when(this.userBusiness.findById(any())).thenReturn(userToUpdate);
		ResponseEntity<?> response = this.adminControllerImpl.updateResearcher(dto);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		
		verify(this.userBusiness, times(1)).findById(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.subjectBusiness);
	}
	
	@Test
	public void updateResearcherPassNullAndUpdateExceptionTest() {
		UserToUpdateDto dto = new UserToUpdateDto();
		dto.setId("23");
		UserEntity userToUpdate = new UserEntity();
		userToUpdate.setRole(Role.RESEARCHER.name());
		userToUpdate.setPassword("123456");
		dto.setName("JUAN");
		dto.setSurname("GARCÍA");
		
		

		when(this.userBusiness.updateUser(any())).thenThrow(new IllegalArgumentException());
		when(this.userBusiness.findById(any())).thenReturn(userToUpdate);
		ResponseEntity<?> response = this.adminControllerImpl.updateResearcher(dto);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.userBusiness, times(1)).findById(any());
		verify(this.userBusiness, times(1)).updateUser(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.subjectBusiness);
	}
	
	@Test
	public void updateResearcherPassEmptyAndUpdateExceptionTest() {
		UserToUpdateDto dto = new UserToUpdateDto();
		dto.setId("23");
		UserEntity userToUpdate = new UserEntity();
		userToUpdate.setRole(Role.RESEARCHER.name());
		userToUpdate.setPassword("123456");
		dto.setName("JUAN");
		dto.setSurname("GARCÍA");
		dto.setPassword("");
		UserEntity userUpdated = null;
		
		when(this.userBusiness.updateUser(any())).thenReturn(userUpdated);
		when(this.userBusiness.findById(any())).thenReturn(userToUpdate);
		ResponseEntity<?> response = this.adminControllerImpl.updateResearcher(dto);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		
		verify(this.userBusiness, times(1)).findById(any());
		verify(this.userBusiness, times(1)).updateUser(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.subjectBusiness);
	}
	
	@Test
	public void updateResearcherOKTest() {
		UserToUpdateDto dto = new UserToUpdateDto();
		dto.setId("23");
		UserEntity userToUpdate = new UserEntity();
		userToUpdate.setRole(Role.RESEARCHER.name());
		userToUpdate.setPassword("123456");
		dto.setName("JUAN");
		dto.setSurname("GARCÍA");
		dto.setPassword("12345678");
		UserEntity userUpdated = new UserEntity();
		
		when(this.userBusiness.updateUser(any())).thenReturn(userUpdated);
		when(this.userBusiness.findById(any())).thenReturn(userToUpdate);
		ResponseEntity<?> response = this.adminControllerImpl.updateResearcher(dto);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		verify(this.userBusiness, times(1)).findById(any());
		verify(this.userBusiness, times(1)).updateUser(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.subjectBusiness);
	}
	
	
	@Test
	public void getAllCompletedInvestigationsExceptionTest() {

		when(this.subjectBusiness.getAllSubjects()).thenThrow(new IllegalArgumentException());
		ResponseEntity<?> response = this.adminControllerImpl.getAllCompletedInvestigations();
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.subjectBusiness, times(1)).getAllSubjects();
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getAllCompletedInvestigationsEmptyListTest() {
		List<SubjectEntity> listSubjects = new ArrayList<>();

		when(this.subjectBusiness.getAllSubjects()).thenReturn(listSubjects);
		ResponseEntity<?> response = this.adminControllerImpl.getAllCompletedInvestigations();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(0, ((InvestigationToEditListDto)response.getBody()).getList().size());

		
		verify(this.subjectBusiness, times(1)).getAllSubjects();
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getAllCompletedInvestigationsNotEmptyListNoInvestigationsTest() {
		List<SubjectEntity> listSubjects = new ArrayList<>();
		SubjectEntity s1 = new SubjectEntity();
		List<InvestigationEntity> investigations = new ArrayList<>();
		s1.setInvestigations(investigations);

		listSubjects.add(s1);

		when(this.subjectBusiness.getAllSubjects()).thenReturn(listSubjects);
		ResponseEntity<?> response = this.adminControllerImpl.getAllCompletedInvestigations();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(0, ((InvestigationToEditListDto)response.getBody()).getList().size());
		
		verify(this.subjectBusiness, times(1)).getAllSubjects();
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getAllCompletedInvestigationsNotEmptyListWithInvestigationsTest() {
		List<SubjectEntity> listSubjects = new ArrayList<>();
		SubjectEntity s1 = new SubjectEntity();
		s1.setIdentificationNumber("11111111");

		List<InvestigationEntity> investigations = new ArrayList<>();
		InvestigationEntity i1 = new InvestigationEntity();
		i1.setCompleted(false);
		InvestigationEntity i2 = new InvestigationEntity();
		i2.setCompleted(true);
		i2.setNumberInvestigation(1);
		InvestigationEntityDetails ied1 = new InvestigationEntityDetails();
		ied1.setId(23);
		i2.setInvestigationEntityDetails(ied1);

		investigations.add(i1);
		investigations.add(i2);

		s1.setInvestigations(investigations);

		listSubjects.add(s1);
		
		when(this.subjectBusiness.getAllSubjects()).thenReturn(listSubjects);
		ResponseEntity<?> response = this.adminControllerImpl.getAllCompletedInvestigations();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, ((InvestigationToEditListDto)response.getBody()).getList().size());
		assertEquals(ied1.getId(), ((InvestigationToEditListDto)response.getBody()).getList().get(0).getInvestigationDetailsId());
		assertEquals(i2.getNumberInvestigation(), ((InvestigationToEditListDto)response.getBody()).getList().get(0).getNumberInvestigation());
		assertEquals(s1.getIdentificationNumber(), ((InvestigationToEditListDto)response.getBody()).getList().get(0).getSubjectIdentificationNumber());

		verify(this.subjectBusiness, times(1)).getAllSubjects();
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	
	@Test
	public void getAllCompletedInvestigationsByIdentificationNumberFindExceptionTest() {
		String identificationNumber = "12345678A";
		when(this.subjectBusiness.getSubjectFromIdentificationNumber(any())).thenThrow(new IllegalArgumentException());
		
		ResponseEntity<?> response = this.adminControllerImpl.getAllCompletedInvestigationsByIdentificationNumber(identificationNumber);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.subjectBusiness, times(1)).getSubjectFromIdentificationNumber(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getAllCompletedInvestigationsByIdentificationNumberFindNullTest() {
		String identificationNumber = "12345678A";
		when(this.subjectBusiness.getSubjectFromIdentificationNumber(any())).thenReturn(null);
		
		ResponseEntity<?> response = this.adminControllerImpl.getAllCompletedInvestigationsByIdentificationNumber(identificationNumber);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		
		verify(this.subjectBusiness, times(1)).getSubjectFromIdentificationNumber(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getAllCompletedInvestigationsByIdentificationNumberEmptyListTest() {
		String identificationNumber = "12345678A";
		SubjectEntity subject = new SubjectEntity();
		when(this.subjectBusiness.getSubjectFromIdentificationNumber(any())).thenReturn(subject);
		
		ResponseEntity<?> response = this.adminControllerImpl.getAllCompletedInvestigationsByIdentificationNumber(identificationNumber);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(0, ((InvestigationToEditListDto)response.getBody()).getList().size());

		
		verify(this.subjectBusiness, times(1)).getSubjectFromIdentificationNumber(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getAllCompletedInvestigationsByIdentificationNumberNotEmptyListTest() {
		String identificationNumber = "12345678A";
		SubjectEntity subject = new SubjectEntity();
		subject.setIdentificationNumber("1");
	
		List<InvestigationEntity> investigations = new ArrayList<>();
		InvestigationEntity investigation1 = new InvestigationEntity();
		investigation1.setCompleted(true);
		investigation1.setNumberInvestigation(1);

		InvestigationEntityDetails investigationEntityDetails = new InvestigationEntityDetails();
		investigationEntityDetails.setId(2);
		investigation1.setInvestigationEntityDetails(investigationEntityDetails);

		InvestigationEntity investigation2 = new InvestigationEntity();
		investigation2.setCompleted(false);
		investigations.add(investigation1);
		investigations.add(investigation2);

		subject.setInvestigations(investigations);
		
		when(this.subjectBusiness.getSubjectFromIdentificationNumber(any())).thenReturn(subject);
		
		ResponseEntity<?> response = this.adminControllerImpl.getAllCompletedInvestigationsByIdentificationNumber(identificationNumber);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, ((InvestigationToEditListDto)response.getBody()).getList().size());
		assertEquals("1", ((InvestigationToEditListDto)response.getBody()).getList().get(0).getSubjectIdentificationNumber());
		assertEquals(new Integer(1), ((InvestigationToEditListDto)response.getBody()).getList().get(0).getNumberInvestigation());
		assertEquals(new Integer(2), ((InvestigationToEditListDto)response.getBody()).getList().get(0).getInvestigationDetailsId());

		verify(this.subjectBusiness, times(1)).getSubjectFromIdentificationNumber(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	
	@Test
	public void getAppointmentDetailsInvalidFormatTest() {
		String investigationsDetailsId = "INVALID_ID";
		
		ResponseEntity<?> response = this.adminControllerImpl.getAppointmentDetails(investigationsDetailsId);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getAppointmentDetailsIFindExceptionTest() {
		String investigationsDetailsId = "2";
		
		when(this.subjectBusiness.getInvestigationDetailsFromId(any())).thenThrow(new IllegalArgumentException());

		ResponseEntity<?> response = this.adminControllerImpl.getAppointmentDetails(investigationsDetailsId);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.subjectBusiness, times(1)).getInvestigationDetailsFromId(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getAppointmentDetailsInvestigationNullTest() {
		String investigationsDetailsId = "2";
		
		when(this.subjectBusiness.getInvestigationDetailsFromId(any())).thenReturn(null);

		ResponseEntity<?> response = this.adminControllerImpl.getAppointmentDetails(investigationsDetailsId);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		
		verify(this.subjectBusiness, times(1)).getInvestigationDetailsFromId(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void getAppointmentDetailsOKTest() {
		String investigationsDetailsId = "2";
		InvestigationEntityDetails investigationDetails = new InvestigationEntityDetails();
		
		when(this.subjectBusiness.getInvestigationDetailsFromId(any())).thenReturn(investigationDetails);

		ResponseEntity<?> response = this.adminControllerImpl.getAppointmentDetails(investigationsDetailsId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		assertNotNull(((InvestigationDetailsToShowDto)response.getBody()));

		verify(this.subjectBusiness, times(1)).getInvestigationDetailsFromId(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	
	@Test
	public void updateInvestigationDetaxilsUpdateExceptionTest() {
		InvestigationDetailsToUpdateDto investigationDetailsToUpdate = new InvestigationDetailsToUpdateDto();
		
		when(this.subjectBusiness.updateInvestigationDetails(any())).thenThrow(new IllegalArgumentException());

		ResponseEntity<?> response = this.adminControllerImpl.updateInvestigationDetails(investigationDetailsToUpdate);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.subjectBusiness, times(1)).updateInvestigationDetails(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	@Test
	public void updateInvestigationDetaxilsOKTest() {
		InvestigationDetailsToUpdateDto investigationDetailsToUpdate = new InvestigationDetailsToUpdateDto();
		
		ResponseEntity<?> response = this.adminControllerImpl.updateInvestigationDetails(investigationDetailsToUpdate);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		verify(this.subjectBusiness, times(1)).updateInvestigationDetails(any());
		verifyZeroInteractions(this.researcherBusiness);
		verifyZeroInteractions(this.userBusiness);
	}
	
	
	@Test
	public void getAllInvestigationDetailsAdminFindExceptionTest() {		
		when(this.researcherBusiness.getAllSubjects()).thenThrow(new IllegalArgumentException());

		ResponseEntity<?> response = this.adminControllerImpl.getAllInvestigationDetailsAdmin();
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		verify(this.researcherBusiness, times(1)).getAllSubjects();
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);	
	}
	
	@Test
	public void getAllInvestigationDetailsAdminListNullTest() {	
		List<SubjectEntity> listSubjects = null;
		
		when(this.researcherBusiness.getAllSubjects()).thenReturn(listSubjects);

		ResponseEntity<?> response = this.adminControllerImpl.getAllInvestigationDetailsAdmin();
		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
		
		verify(this.researcherBusiness, times(1)).getAllSubjects();
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);	
	}
	
	@Test
	public void getAllInvestigationDetailsAdminEmptyListTest() {	
		List<SubjectEntity> listSubjects = new ArrayList<>();
		
		when(this.researcherBusiness.getAllSubjects()).thenReturn(listSubjects);

		ResponseEntity<?> response = this.adminControllerImpl.getAllInvestigationDetailsAdmin();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(0, ((InvestigationDetailsToShowListDto)response.getBody()).getList().size());

		verify(this.researcherBusiness, times(1)).getAllSubjects();
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);	
	}
	
	@Test
	public void getAllInvestigationDetailsAdminNotEmptyListTest() {	
		List<SubjectEntity> listSubjects = new ArrayList<>();
		SubjectEntity s1 = new SubjectEntity();
		s1.setIdentificationNumber("11111111");
		
		List<InvestigationEntity> l1 = new ArrayList<>();
		
		InvestigationEntity i1 = new InvestigationEntity();
		i1.setCompleted(true);
		i1.setInvestigationEntityDetails(new InvestigationEntityDetails());
		
		InvestigationEntity i2 = new InvestigationEntity();
		i2.setCompleted(false);
		i2.setInvestigationEntityDetails(new InvestigationEntityDetails());

		l1.add(i1);
		l1.add(i2);

		s1.setInvestigations(l1);
		
		listSubjects.add(s1);

		when(this.researcherBusiness.getAllSubjects()).thenReturn(listSubjects);

		ResponseEntity<?> response = this.adminControllerImpl.getAllInvestigationDetailsAdmin();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(2, ((InvestigationDetailsToShowListDto)response.getBody()).getList().size());

		assertEquals("11111111", ((InvestigationDetailsToShowListDto)response.getBody()).getList().get(0).getIdentificationNumber());
		assertEquals("11111111", ((InvestigationDetailsToShowListDto)response.getBody()).getList().get(1).getIdentificationNumber());
		
		assertNotNull(((InvestigationDetailsToShowListDto)response.getBody()).getList().get(0));
		assertNotNull(((InvestigationDetailsToShowListDto)response.getBody()).getList().get(1));

		verify(this.researcherBusiness, times(1)).getAllSubjects();
		verifyZeroInteractions(this.subjectBusiness);
		verifyZeroInteractions(this.userBusiness);	
	}
}
