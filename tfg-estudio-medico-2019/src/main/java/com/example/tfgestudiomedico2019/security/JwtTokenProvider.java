package com.example.tfgestudiomedico2019.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${secretKey}")
    private String secretKey;
    
    @Value("${expirationTime-ms}")
    private Long tokenExpirationTime;


    @Value("${app.jwt.header.string}")
    private String jwtHeaderString;

    

    public String generateJwtToken(Authentication authentication){
    	
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining());

        Date expirationDate = new Date(System.currentTimeMillis() + tokenExpirationTime);
        
        return Jwts.builder().setSubject(authentication.getName())
        	.claim("role", authorities)
        	.setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }

    public Authentication getAuthentication(HttpServletRequest request){
    	
    	String token;
    	
    	String tokenAux = request.getHeader(jwtHeaderString);
        if(tokenAux != null && tokenAux.startsWith("Bearer")){
            token = tokenAux.substring(7, tokenAux.length());
        }
        else {
        	return null;
        }
    	
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        
        String username = claims.getSubject();
        
        List<GrantedAuthority> authorities = Arrays.stream(claims.get("role").toString().split(","))
        		.map(role -> role.startsWith("ROLE_")?role:"ROLE_"+role)
        		.map(SimpleGrantedAuthority::new)
        		.collect(Collectors.toList());
        
        if(username == null || authorities == null) {
        	return null;
        }
        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }

    public boolean validateToken(HttpServletRequest request){
    	String token;
    	
    	String tokenAux = request.getHeader(jwtHeaderString);
        if(tokenAux != null && tokenAux.startsWith("Bearer")){
            token = tokenAux.substring(7, tokenAux.length());
        }
        else {
        	return false;
        }
        
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        if(claims.getExpiration().before(new Date())){
            return false;
        }
        return true;
    }
    
}
