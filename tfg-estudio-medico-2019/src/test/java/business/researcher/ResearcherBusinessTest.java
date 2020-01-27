package business.researcher;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.tfgestudiomedico2019.business.researcher.ResearcherBusinessImpl;
import com.example.tfgestudiomedico2019.model.entity.InvestigationEntity;
import com.example.tfgestudiomedico2019.model.entity.SubjectEntity;
import com.example.tfgestudiomedico2019.repository.InvestigationRepository;
import com.example.tfgestudiomedico2019.repository.SubjectRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ResearcherBusinessTest {

	@InjectMocks
	private ResearcherBusinessImpl researcherBusinessImpl;
	
	@Mock
	private SubjectRepository subjectRepository;
	
	@Mock
	private InvestigationRepository investigationRepository;
	
	
	@Test(expected = Exception.class)
	public void getAllSubjectsAndInvestigationsByResearcherExceptionTest() {
		Integer idResearcher = 1;
		
		when(this.subjectRepository.findByResearcher(any())).thenThrow(new Exception());
		this.researcherBusinessImpl.getAllSubjectsAndInvestigationsByResearcher(idResearcher);
	}
	
	@Test
	public void getAllSubjectsAndInvestigationsByResearcherOKTest() {
		Integer idResearcher = 1;
		List<SubjectEntity> list = new ArrayList<>();
		
		when(this.subjectRepository.findByResearcher(any())).thenReturn(list);
		List<SubjectEntity> listReturn = this.researcherBusinessImpl.getAllSubjectsAndInvestigationsByResearcher(idResearcher);
		
		assertEquals(0, listReturn.size());
		verify(this.subjectRepository, times(1)).findByResearcher(any());
		verifyZeroInteractions(this.investigationRepository);
	}
	
	
	@Test(expected = Exception.class)
	public void getAllSubjectsExceptionTest() {
		when(this.subjectRepository.findAll()).thenThrow(new Exception());
		this.researcherBusinessImpl.getAllSubjects();	
	}
	
	@Test
	public void getAllSubjectsOKTest() {
		List<SubjectEntity> list = new ArrayList<>();
		when(this.subjectRepository.findAll()).thenReturn(list);
		List<SubjectEntity> listReturn = this.researcherBusinessImpl.getAllSubjects();	
		assertEquals(0, listReturn.size());
		verify(this.subjectRepository, times(1)).findAll();
		verifyZeroInteractions(this.investigationRepository);
	}
	
	
	@Test(expected = Exception.class)
	public void getInvestigationBySubjectAndNumberInvestigationExceptionTest() {
		Integer idSubject = 1;
		Integer numberInvestigation = 2;
		when(this.investigationRepository.findBySubjectAndNumberInvestigation(any(), any())).thenThrow(new Exception());
		this.researcherBusinessImpl.getInvestigationBySubjectAndNumberInvestigation(idSubject, numberInvestigation);	
	}
	
	@Test
	public void getInvestigationBySubjectAndNumberInvestigationOKTest() {
		Integer idSubject = 1;
		Integer numberInvestigation = 2;
		InvestigationEntity investigationEntity = new InvestigationEntity();
		when(this.investigationRepository.findBySubjectAndNumberInvestigation(any(), any())).thenReturn(investigationEntity);
		this.researcherBusinessImpl.getInvestigationBySubjectAndNumberInvestigation(idSubject, numberInvestigation);
		verify(this.investigationRepository, times(1)).findBySubjectAndNumberInvestigation(any(), any());
		verifyZeroInteractions(this.subjectRepository);
	}
	
	
	@Test(expected = Exception.class)
	public void saveInvestigationDetailsExceptionTest() {
		InvestigationEntity investigationEntity = new InvestigationEntity();
		
		when(this.investigationRepository.save(any())).thenThrow(new Exception());
		this.researcherBusinessImpl.saveInvestigationDetails(investigationEntity);	
	}
	
	@Test
	public void saveInvestigationDetailsFalseTest() {
		InvestigationEntity investigationEntity = new InvestigationEntity();
		InvestigationEntity InvestigationSaved = null;
		
		when(this.investigationRepository.save(any())).thenReturn(InvestigationSaved);
		Boolean success = this.researcherBusinessImpl.saveInvestigationDetails(investigationEntity);
		
		assertFalse(success);
		verify(this.investigationRepository, times(1)).save(any());
		verifyZeroInteractions(this.subjectRepository);
	}
	
	@Test
	public void saveInvestigationDetailsTrueTest() {
		InvestigationEntity investigationEntity = new InvestigationEntity();
		InvestigationEntity InvestigationSaved = new InvestigationEntity();
		
		when(this.investigationRepository.save(any())).thenReturn(InvestigationSaved);
		Boolean success = this.researcherBusinessImpl.saveInvestigationDetails(investigationEntity);
		
		assertTrue(success);
		verify(this.investigationRepository, times(1)).save(any());
		verifyZeroInteractions(this.subjectRepository);
	}

	
	@Test(expected = Exception.class)
	public void getAllSubjectsByResearcherExceptionTest() {
		Integer idResearcher = 1;
		when(this.subjectRepository.findByResearcher(any())).thenThrow(new Exception());
		this.researcherBusinessImpl.getAllSubjectsByResearcher(idResearcher);
	}
	
	@Test
	public void getAllSubjectsByResearcherOKTest() {
		Integer idResearcher = 1;
		List<SubjectEntity> list = new ArrayList<>();

		when(this.subjectRepository.findByResearcher(any())).thenReturn(list);
		this.researcherBusinessImpl.getAllSubjectsByResearcher(idResearcher);
		
		verify(this.subjectRepository, times(1)).findByResearcher(any());
		verifyZeroInteractions(this.investigationRepository);
	}
	
	
}
