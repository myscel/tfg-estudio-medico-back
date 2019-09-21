package com.example.tfgestudiomedico2019.model.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUserDetails implements UserDetails {
	
	private String username;
	private String token;
	private Long id;
	private Collection<? extends GrantedAuthority> authorities;
	
	public JwtUserDetails(String username, long id, String token, List<GrantedAuthority> grantedAuthorities) {
		this.username = username;
		this.id = id;
		this.token = token;
		this.authorities = grantedAuthorities;
	}

	
	

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getToken() {
		return token;
	}



	public Long getId() {
		return id;
	}




	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	
	
}
