package com.example.tfgestudiomedico2019.security;

import org.springframework.stereotype.Component;

import com.example.tfgestudiomedico2019.model.entity.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {

	public String generate(JwtUser jwtUser) {
		
		
		Claims claims = Jwts.claims()
				.setSubject(jwtUser.getUsername());
		claims.put("role", jwtUser.getRole());
		
		return Jwts.builder()
			.setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, "patata")
				.compact();
	}

}
