package com.example.tfgestudiomedico2019.business.subject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.tfgestudiomedico2019.business.user.UserBusinessImpl;
import com.example.tfgestudiomedico2019.model.entity.Role;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.repository.UserRepository;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class UserBusinessTest {

	@InjectMocks
	private UserBusinessImpl userBusinessImpl;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	
	
	@Test
	public void saveUserTestOK() {
		String passNotEncoded = "pass1Encoded";
		String passEncoded = "pass1NotEncoded";
		
		UserEntity userEntity = new UserEntity();	
		userEntity.setPassword(passNotEncoded);
		
		when(this.userRepository.save(any())).thenReturn(userEntity);
		when(this.passwordEncoder.encode(any())).thenReturn(passEncoded);
		
		this.userBusinessImpl.saveUser(userEntity);
		
		assertEquals(passEncoded, userEntity.getPassword());
		assertNotEquals(passNotEncoded, userEntity.getPassword());
	}
	
	@Test
	public void findByUsernameOKTest() {
		String username = "47298046H";
		UserEntity userEntity = new UserEntity();
				
		when(this.userRepository.findByUsername(any())).thenReturn(userEntity);
		
		this.userBusinessImpl.findByUsername(username);
	}
	
	@Test
	public void getAllResearchersTest() {
		List<UserEntity> list = new ArrayList<>();
				
		when(this.userRepository.findByRole(any())).thenReturn(list);
		
		this.userBusinessImpl.getAllResearchers();
	}
}
