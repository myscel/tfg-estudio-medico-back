package com.example.tfgestudiomedico2019.jwt;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.List;

@Component
public class JwtTokenProvider {
	
	@Value("${jwt.secret}")
	private String jwtSecret;
	
	@Value("${jwt.token.prefixt}")
	private String jwtTokenPrefix;
	
	@Value("${jwt.header.string}")
	private String jwtHeaderString;
	
	@Value("${jwt.expiration-in-ms}")
	private Long jwtExpirationInMs;
	
	public String generateToken(Authentication authentication) {
		String authorities = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining());
		
		return Jwts.builder().setSubject(authentication.getName())
				.claim("roles", authorities)
				.setExpiration(new Date(System.currentTimeMillis() + this.jwtExpirationInMs))
				.signWith(SignatureAlgorithm.HS512, this.jwtSecret).compact();
	}
	
	public Authentication getAuthentication(HttpServletRequest request) {
		String swt = resolveToken(request);
		
		if(swt == null) {
			return null;
		}
		
		Claims claims = Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(swt).getBody();
		
		String username = claims.getSubject();
		
		final List<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
				.map(role -> role.startsWith("ROLE ")?role: "ROLE " + role)
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
				
		return username != null ? new UsernamePasswordAuthenticationToken(username,  null) : null;
	}
	
	public boolean validateToken(HttpServletRequest request) {
		String swt = resolveToken(request);
	
		if(swt == null) {
			return false;
		}
		
		Claims claims = Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(swt).getBody();
		
		
		if(claims.getExpiration().before(new Date())) {
			return false;
		}
		return true;
		
	}
	
	public String resolveToken(HttpServletRequest request) {
		//Bearer key
		String bearerSwt = request.getHeader(this.jwtHeaderString);
		
		if(bearerSwt != null && bearerSwt.startsWith(this.jwtTokenPrefix)) {
			return bearerSwt.substring(7, bearerSwt.length());
		}
		
		return null;
	}

}
