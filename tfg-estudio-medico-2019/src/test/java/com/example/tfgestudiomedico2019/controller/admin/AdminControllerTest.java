package com.example.tfgestudiomedico2019.controller.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.tfgestudiomedico2019.business.user.UserBusiness;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class AdminControllerTest {

	@InjectMocks
	private AdminControllerImpl adminControllerImpl;
	
	@Mock
	private UserBusiness userBusiness;
	
	
	@Test
	public void getAllUsersTest() {
		when(this.userBusiness.getAllResearchers()).thenThrow(new NullPointerException("Error occurred"));
		
		ResponseEntity<?> response = this.adminControllerImpl.getAllUsers();
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
}
