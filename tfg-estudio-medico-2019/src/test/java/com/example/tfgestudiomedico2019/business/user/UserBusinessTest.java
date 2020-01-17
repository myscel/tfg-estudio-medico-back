package com.example.tfgestudiomedico2019.business.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.tfgestudiomedico2019.business.user.UserBusinessImpl;
import com.example.tfgestudiomedico2019.model.entity.UserEntity;
import com.example.tfgestudiomedico2019.repository.UserRepository;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserBusinessTest {

	@InjectMocks
	private UserBusinessImpl userBusinessImpl;
	
	@Mock
	private UserRepository userRepository;
	
	
	@Test
	public void saveUserTestOK() {
		String pass = "pass1";
		
		UserEntity userEntity = new UserEntity();	
		userEntity.setPassword(pass);
		
		when(this.userRepository.save(any())).thenReturn(userEntity);
		
		this.userBusinessImpl.saveUser(userEntity);
		
		assertEquals(pass, userEntity.getPassword());
		verify(userRepository, times(1)).save(any());
	}
	
	@Test
	public void findByUsernameOKTest() {
		String username = "47298046H";
		UserEntity userEntity = new UserEntity();
				
		when(this.userRepository.findByUsername(any())).thenReturn(userEntity);
		
		this.userBusinessImpl.findByUsername(username);
		verify(userRepository, times(1)).findByUsername(any());
	}
	
	@Test
	public void getAllResearchersTest() {
		List<UserEntity> list = new ArrayList<>();
				
		when(this.userRepository.findByRole(any())).thenReturn(list);
		
		this.userBusinessImpl.getAllResearchers();
		verify(userRepository, times(1)).findByRole(any());
	}
	
	@Test
	public void deleteResearcherTestKOTest() {
		String username = "47298046H";
		
		when(this.userRepository.deleteByUsername(any())).thenReturn(0L);
		
		boolean response = this.userBusinessImpl.deleteResearcher(username);
		
		assertEquals(false, response);
		verify(userRepository, times(1)).deleteByUsername(any());
	}
	
	@Test
	public void deleteResearcherTestOKTest() {
		String username = "47298046H";
		
		when(this.userRepository.deleteByUsername(any())).thenReturn(1L);
		
		boolean response = this.userBusinessImpl.deleteResearcher(username);
		
		assertEquals(true, response);
		verify(userRepository, times(1)).deleteByUsername(any());
	}
	
	@Test
	public void findByIdTest() {
		Integer id = 1;
		UserEntity userEntity = new UserEntity();
		
		when(this.userRepository.findById(anyInt())).thenReturn(userEntity);
		
		this.userBusinessImpl.findById(id);
		verify(userRepository, times(1)).findById(anyInt());
	}
	
	@Test
	public void updateUserAllFieldsNullTest() {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(1);
		userEntity.setPassword(null);
		userEntity.setName(null);
		userEntity.setSurname(null);
		
		when(this.userRepository.findById(anyInt())).thenReturn(userEntity);
		
		this.userBusinessImpl.updateUser(userEntity);
		verify(userRepository, times(1)).findById(anyInt());
		verify(userRepository, times(1)).save(any());
	}
	
	@Test
	public void updateUserAllFieldsEmptyTest() {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(1);
		userEntity.setPassword("");
		userEntity.setName("");
		userEntity.setSurname("");
		
		when(this.userRepository.findById(anyInt())).thenReturn(userEntity);
		
		this.userBusinessImpl.updateUser(userEntity);
		verify(userRepository, times(1)).findById(anyInt());
		verify(userRepository, times(1)).save(any());
	}
	
	@Test
	public void updateUserAllFieldsNotEmptyTest() {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(1);
		
		String passwordToUpdate = "123456";
		String nameToUpdate = "Eduardo";
		String surnameToUpdate = "Gonzalo Montero";
		
		userEntity.setPassword(passwordToUpdate);
		userEntity.setName(nameToUpdate);
		userEntity.setSurname(surnameToUpdate);
		
		when(this.userRepository.findById(anyInt())).thenReturn(userEntity);
		
		this.userBusinessImpl.updateUser(userEntity);
		verify(userRepository, times(1)).findById(anyInt());
		verify(userRepository, times(1)).save(any());
	}

}
