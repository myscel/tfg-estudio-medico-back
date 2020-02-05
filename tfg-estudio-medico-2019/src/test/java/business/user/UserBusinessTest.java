package business.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.example.tfgestudiomedico2019.business.user.UserBusinessImpl;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.repository.UserRepository;


@RunWith(MockitoJUnitRunner.class)
public class UserBusinessTest {
	
	@InjectMocks
	private UserBusinessImpl userBusinessImpl;
	
	@Mock
    private UserRepository userRepository;
	
	@Test(expected = Exception.class)
	public void findByUsernameAndPasswordExceptionTest() {
		String username = "12345678A";
		String password = "123456";

		when(this.userRepository.findByUsernameAndPassword(any(), any())).thenThrow(new Exception());
		this.userBusinessImpl.findByUsernameAndPassword(username, password);
	}
	
	@Test
	public void findByUsernameAndPasswordOKTest() {
		String username = "12345678A";
		String password = "123456";
		UserEntity userEntity = new UserEntity();

		when(this.userRepository.findByUsernameAndPassword(any(), any())).thenReturn(userEntity);
		this.userBusinessImpl.findByUsernameAndPassword(username, password);
		verify(this.userRepository, times(1)).findByUsernameAndPassword(any(), any());
	}
	
	
	@Test(expected = Exception.class)
	public void saveUserExceptionTest() {
		UserEntity userEntity = new UserEntity();

		when(this.userRepository.save(any())).thenThrow(new Exception());
		this.userBusinessImpl.saveUser(userEntity);
	}
	
	@Test
	public void saveUserOKTest() {
		UserEntity userEntity = new UserEntity();
		UserEntity userSaved = new UserEntity();

		when(this.userRepository.save(any())).thenReturn(userSaved);
		this.userBusinessImpl.saveUser(userEntity);
		verify(this.userRepository, times(1)).save(any());

	}

	
	@Test(expected = Exception.class)
	public void findByUsernameExceptionTest() {
		String username = "12345678A";

		when(this.userRepository.findByUsername(any())).thenThrow(new Exception());
		this.userBusinessImpl.findByUsername(username);
	}
	
	@Test
	public void findByUsernameOKTest() {
		String username = "12345678A";
		UserEntity userEntity = new UserEntity();

		when(this.userRepository.findByUsername(any())).thenReturn(userEntity);
		this.userBusinessImpl.findByUsername(username);
		verify(this.userRepository, times(1)).findByUsername(any());
	}
	
	
	@Test(expected = Exception.class)
	public void getAllResearchersExceptionTest() {
		when(this.userRepository.findByRole(any())).thenThrow(new Exception());
		this.userBusinessImpl.getAllResearchers();
	}
	
	@Test
	public void getAllResearchersOKTest() {
		List<UserEntity> list = new ArrayList<>();
		when(this.userRepository.findByRole(any())).thenReturn(list);
		this.userBusinessImpl.getAllResearchers();
		verify(this.userRepository, times(1)).findByRole(any());
	}
	
	
	@Test(expected = Exception.class)
	public void deleteResearcherExceptionTest() {
		String username = "12345678A";
		
		when(this.userRepository.deleteByUsername(any())).thenThrow(new Exception());
		this.userBusinessImpl.deleteResearcher(username);
	}
	
	@Test
	public void deleteResearcherNotFoundTest() {
		String username = "12345678A";
		
		when(this.userRepository.deleteByUsername(any())).thenReturn(0L);
		Boolean success = this.userBusinessImpl.deleteResearcher(username);
		assertFalse(success);
	}
	
	@Test
	public void deleteResearcherOKTest() {
		String username = "12345678A";
		
		when(this.userRepository.deleteByUsername(any())).thenReturn(1L);
		Boolean success = this.userBusinessImpl.deleteResearcher(username);
		assertTrue(success);
		verify(this.userRepository, times(1)).deleteByUsername(any());
	}
	
	
	@Test(expected = Exception.class)
	public void findByIdExceptionTest() {
		Integer id = 1;
		when(this.userRepository.findById(anyInt())).thenThrow(new Exception());
		this.userBusinessImpl.findById(id);
	}
	
	@Test
	public void findByIdOKTest() {
		Integer id = 1;
		UserEntity userEntity = new UserEntity();
		when(this.userRepository.findById(anyInt())).thenReturn(userEntity);
		this.userBusinessImpl.findById(id);
		verify(this.userRepository, times(1)).findById(anyInt());
	}
	
	
	
	
	
	@Test(expected = Exception.class)
	public void updateUserExceptionTest() {
		UserEntity userEntity = new UserEntity();

		when(this.userRepository.save(any())).thenThrow(new Exception());
		this.userBusinessImpl.updateUser(userEntity);
	}
	
	@Test
	public void updateUserOKTest() {
		UserEntity userEntity = new UserEntity();
		UserEntity userEntityUpdated = new UserEntity();

		when(this.userRepository.save(any())).thenReturn(userEntityUpdated);
		this.userBusinessImpl.updateUser(userEntity);
		verify(this.userRepository, times(1)).save(any());

	}
	
}
