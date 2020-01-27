package business.subject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.tfgestudiomedico2019.business.subject.SubjectBusinessImpl;
import com.example.tfgestudiomedico2019.model.entity.InvestigationEntity;
import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.repository.SubjectRepository;
import com.example.tfgestudiomedico2019.repository.UserRepository;


@RunWith(MockitoJUnitRunner.class)
public class SubjectBusinessTest {

	@InjectMocks
	private SubjectBusinessImpl subjectBusinessImpl;
	

	@Mock
	private SubjectRepository subjectRepository;
	
	@Mock
	private UserRepository userRepository;
	
	@Test(expected = Exception.class)
	public void saveSubjectExceptionTest() {
		SubjectEntity subject = new SubjectEntity();
		
		when(this.subjectRepository.save(any())).thenThrow(new Exception());
		this.subjectBusinessImpl.saveSubject(subject);
	}
	
	@Test
	public void saveSubjectOKTest() {
		SubjectEntity subject = new SubjectEntity();
		
		when(this.subjectRepository.save(any())).thenReturn(subject);
		this.subjectBusinessImpl.saveSubject(subject);
		
		verify(this.subjectRepository, times(1)).save(any());
		verifyZeroInteractions(this.userRepository);
	}
	
	
	@Test(expected = Exception.class)
	public void deleteSubjectByIdentificationNumberExceptionTest() {
		Integer identificationNumber = 24;
		
		when(this.subjectRepository.deleteByIdentificationNumber(any())).thenThrow(new Exception());		
		this.subjectBusinessImpl.deleteSubjectByIdentificationNumber(identificationNumber);
	}
	
	@Test
	public void deleteSubjectByIdentificationNumberFalseTest() {
		Integer identificationNumber = 24;
		Long rowsDeleted = 0L;
		
		when(this.subjectRepository.deleteByIdentificationNumber(any())).thenReturn(rowsDeleted);		
		Boolean success = this.subjectBusinessImpl.deleteSubjectByIdentificationNumber(identificationNumber);
		assertFalse(success);
	}
	
	@Test
	public void deleteSubjectByIdentificationNumberTrueTest() {
		Integer identificationNumber = 24;
		Long rowsDeleted = 1L;
		
		when(this.subjectRepository.deleteByIdentificationNumber(any())).thenReturn(rowsDeleted);		
		Boolean success = this.subjectBusinessImpl.deleteSubjectByIdentificationNumber(identificationNumber);
		assertTrue(success);
	}
	
	
	@Test(expected = Exception.class)
	public void getNumberInvestigationsCompletedFromSubjectFindExceptionTest() {
		Integer identificationNumber = 24;
		
		when(this.subjectRepository.findByIdentificationNumber(any())).thenThrow(new Exception());		
		this.subjectBusinessImpl.getNumberInvestigationsCompletedFromSubject(identificationNumber);
	}
	
	@Test
	public void getNumberInvestigationsCompletedFromSubjectInvestigationsNullTest() {
		Integer identificationNumber = 24;
		SubjectEntity subject = new SubjectEntity();
		
		when(this.subjectRepository.findByIdentificationNumber(any())).thenReturn(subject);		
		Integer rows = this.subjectBusinessImpl.getNumberInvestigationsCompletedFromSubject(identificationNumber);
		
		assertEquals(0, rows.shortValue());
		verify(this.subjectRepository, times(1)).findByIdentificationNumber(any());
		verifyZeroInteractions(this.userRepository);
	}
	
	@Test
	public void getNumberInvestigationsCompletedFromSubjectInvestigationsEmptyTest() {
		Integer identificationNumber = 24;
		SubjectEntity subject = new SubjectEntity();
		subject.setInvestigations(new ArrayList<>());
		
		when(this.subjectRepository.findByIdentificationNumber(any())).thenReturn(subject);		
		Integer rows = this.subjectBusinessImpl.getNumberInvestigationsCompletedFromSubject(identificationNumber);
		
		assertEquals(0, rows.shortValue());
		verify(this.subjectRepository, times(1)).findByIdentificationNumber(any());
		verifyZeroInteractions(this.userRepository);
	}
	
	@Test
	public void getNumberInvestigationsCompletedFromSubjectInvestigationsNoCompletedElementsTest() {
		Integer identificationNumber = 24;
		SubjectEntity subject = new SubjectEntity();
		List<InvestigationEntity> investigations = new ArrayList<>();
		
		InvestigationEntity investigationEntity1 = new InvestigationEntity();
		investigationEntity1.setCompleted(false);
		investigations.add(investigationEntity1);
		
		InvestigationEntity investigationEntity2 = new InvestigationEntity();
		investigationEntity2.setCompleted(false);
		investigations.add(investigationEntity2);
		
		subject.setInvestigations(investigations);
		
		when(this.subjectRepository.findByIdentificationNumber(any())).thenReturn(subject);		
		Integer rows = this.subjectBusinessImpl.getNumberInvestigationsCompletedFromSubject(identificationNumber);
		
		assertEquals(0, rows.shortValue());
		verify(this.subjectRepository, times(1)).findByIdentificationNumber(any());
		verifyZeroInteractions(this.userRepository);
	}
	
	@Test
	public void getNumberInvestigationsCompletedFromSubjectInvestigationsAllCompletedElementsTest() {
		Integer identificationNumber = 24;
		SubjectEntity subject = new SubjectEntity();
		List<InvestigationEntity> investigations = new ArrayList<>();
		
		InvestigationEntity investigationEntity1 = new InvestigationEntity();
		investigationEntity1.setCompleted(true);
		investigations.add(investigationEntity1);
		
		InvestigationEntity investigationEntity2 = new InvestigationEntity();
		investigationEntity2.setCompleted(true);
		investigations.add(investigationEntity2);
		
		subject.setInvestigations(investigations);
		
		when(this.subjectRepository.findByIdentificationNumber(any())).thenReturn(subject);		
		Integer rows = this.subjectBusinessImpl.getNumberInvestigationsCompletedFromSubject(identificationNumber);
		
		assertEquals(investigations.size(), rows.shortValue());
		verify(this.subjectRepository, times(1)).findByIdentificationNumber(any());
		verifyZeroInteractions(this.userRepository);
	}
	
	@Test
	public void getNumberInvestigationsCompletedFromSubjectInvestigationsAOnlyOneCompletedElementTest() {
		Integer identificationNumber = 24;
		SubjectEntity subject = new SubjectEntity();
		List<InvestigationEntity> investigations = new ArrayList<>();
		
		InvestigationEntity investigationEntity1 = new InvestigationEntity();
		investigationEntity1.setCompleted(true);
		investigations.add(investigationEntity1);
		
		InvestigationEntity investigationEntity2 = new InvestigationEntity();
		investigationEntity2.setCompleted(false);
		investigations.add(investigationEntity2);
		
		subject.setInvestigations(investigations);
		
		when(this.subjectRepository.findByIdentificationNumber(any())).thenReturn(subject);		
		Integer rows = this.subjectBusinessImpl.getNumberInvestigationsCompletedFromSubject(identificationNumber);
		
		assertEquals(1, rows.shortValue());
		verify(this.subjectRepository, times(1)).findByIdentificationNumber(any());
		verifyZeroInteractions(this.userRepository);
	}
	
	
	@Test(expected = Exception.class)
	public void getSubjectFromIdentificationNumberExceptionTest() {
		Integer identificationNumber = 1;
		
		when(this.subjectRepository.findByIdentificationNumber(any())).thenThrow(new Exception());
		this.subjectBusinessImpl.getSubjectFromIdentificationNumber(identificationNumber);
	}
	
	@Test
	public void getSubjectFromIdentificationNumberOKTest() {
		Integer identificationNumber = 1;
		SubjectEntity subjectEntity = new SubjectEntity();
		
		when(this.subjectRepository.findByIdentificationNumber(any())).thenReturn(subjectEntity);
		this.subjectBusinessImpl.getSubjectFromIdentificationNumber(identificationNumber);
		
		verify(this.subjectRepository, times(1)).findByIdentificationNumber(any());
		verifyZeroInteractions(this.userRepository);
	}
	
	
	@Test(expected = Exception.class)
	public void getSubjectsFromDNIResearcherExceptionTest() {
		String username = "12345678A";
		when(this.userRepository.findByUsername(any())).thenThrow(new Exception());
		this.subjectBusinessImpl.getSubjectsFromDNIResearcher(username);
	}
	
	@Test
	public void getSubjectsFromDNIResearcherNullTest() {
		String username = "12345678A";
		UserEntity researcher = null;
		when(this.userRepository.findByUsername(any())).thenReturn(researcher);
		List<SubjectEntity> list = this.subjectBusinessImpl.getSubjectsFromDNIResearcher(username);
		
		assertNull(list);
		verify(this.userRepository, times(1)).findByUsername(any());
		verifyZeroInteractions(this.subjectRepository);
	}
	
	@Test
	public void getSubjectsFromDNIResearcherNoSubjectsTest() {
		String username = "12345678A";
		UserEntity researcher = new UserEntity();
		researcher.setSubjects(new ArrayList<>());
		
		when(this.userRepository.findByUsername(any())).thenReturn(researcher);
		List<SubjectEntity> list = this.subjectBusinessImpl.getSubjectsFromDNIResearcher(username);
		
		assertEquals(0, list.size());
		verify(this.userRepository, times(1)).findByUsername(any());
		verifyZeroInteractions(this.subjectRepository);
	}
	
	@Test
	public void getSubjectsFromDNIResearcherSubjectsTest() {
		String username = "12345678A";
		UserEntity researcher = new UserEntity();
		List<SubjectEntity> subjects = new ArrayList<>();
		subjects.add(new SubjectEntity());
		subjects.add(new SubjectEntity());
		researcher.setSubjects(subjects);
		
		when(this.userRepository.findByUsername(any())).thenReturn(researcher);
		List<SubjectEntity> list = this.subjectBusinessImpl.getSubjectsFromDNIResearcher(username);
		
		assertEquals(2, list.size());
		verify(this.userRepository, times(1)).findByUsername(any());
		verifyZeroInteractions(this.subjectRepository);
	}
	
	
	@Test(expected = Exception.class)
	public void getAllSubjectsExceptionTest() {
		when(this.userRepository.findAll()).thenThrow(new Exception());
		this.subjectBusinessImpl.getAllSubjects();
	}
	
	@Test
	public void getAllSubjectsOKTest() {
		List<SubjectEntity> list = new ArrayList<>();
		when(this.subjectRepository.findAll()).thenReturn(list);
		this.subjectBusinessImpl.getAllSubjects();
		
		verify(this.subjectRepository, times(1)).findAll();
		verifyZeroInteractions(this.userRepository);
	}
	
}
