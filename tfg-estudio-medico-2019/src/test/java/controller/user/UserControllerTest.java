package controller.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.tfgestudiomedico2019.business.user.UserBusiness;
import com.example.tfgestudiomedico2019.controller.user.UserControllerImpl;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.model.rest.subject.SubjectListFromResearcherDto;
import com.example.tfgestudiomedico2019.model.rest.user.UserLoggedDto;
import com.example.tfgestudiomedico2019.model.rest.user.UserToLoginDto;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	
	@InjectMocks
	private UserControllerImpl userControllerImpl;
	

	@Mock
	private UserBusiness userBusiness;
	
	@Test
	public void loginFindExceptionTest() {
		UserToLoginDto userToLoginDto = new UserToLoginDto();
		userToLoginDto.setUsername("12345678A");
		userToLoginDto.setPassword("pass1");
		
		when(this.userBusiness.findByUsernameAndPassword(any(), any())).thenThrow(new IllegalArgumentException());

		ResponseEntity<?> response = this.userControllerImpl.login(userToLoginDto);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
			
		verify(this.userBusiness, times(1)).findByUsernameAndPassword(any(), any());
	}
	
	@Test
	public void loginFindNullTest() {
		UserToLoginDto userToLoginDto = new UserToLoginDto();
		userToLoginDto.setUsername("12345678A");
		userToLoginDto.setPassword("pass1");
		
		when(this.userBusiness.findByUsernameAndPassword(any(), any())).thenReturn(null);

		ResponseEntity<?> response = this.userControllerImpl.login(userToLoginDto);
		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
			
		verify(this.userBusiness, times(1)).findByUsernameAndPassword(any(), any());
	}
	
	@Test
	public void loginOKTest() {
		UserToLoginDto userToLoginDto = new UserToLoginDto();
		userToLoginDto.setUsername("12345678A");
		userToLoginDto.setPassword("pass1");
		
		UserEntity userLogged = new UserEntity();
		
		when(this.userBusiness.findByUsernameAndPassword(any(), any())).thenReturn(userLogged);

		ResponseEntity<?> response = this.userControllerImpl.login(userToLoginDto);
		assertEquals(HttpStatus.OK, response.getStatusCode());
			
		verify(this.userBusiness, times(1)).findByUsernameAndPassword(any(), any());
		assertNotNull((UserLoggedDto)response.getBody());

	}

}
