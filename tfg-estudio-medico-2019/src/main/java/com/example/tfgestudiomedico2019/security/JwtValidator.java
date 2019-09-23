package com.example.tfgestudiomedico2019.security;

import org.springframework.stereotype.Component;

import com.example.tfgestudiomedico2019.model.entity.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {
	
	private String secret = "patata";

	public JwtUser validate(String token) {
		
		JwtUser jwtUser = null;
		
		try {

			Claims body = Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody();
			
			jwtUser = new JwtUser();
			
			jwtUser.setUsername(body.getSubject());
			jwtUser.setRole((String)body.get("role"));
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		
		return jwtUser;
	}

}
