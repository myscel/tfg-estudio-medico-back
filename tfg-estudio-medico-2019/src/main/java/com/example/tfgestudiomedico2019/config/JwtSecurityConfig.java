package com.example.tfgestudiomedico2019.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.tfgestudiomedico2019.security.JwtAuthenticationEntryPoint;
import com.example.tfgestudiomedico2019.security.JwtAuthenticationProvider;
import com.example.tfgestudiomedico2019.security.JwtAuthenticationTokenFilter;
import com.example.tfgestudiomedico2019.security.JwtSuccessHandler;


@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private  JwtAuthenticationProvider authenticationProvider;
	
	private JwtAuthenticationEntryPoint entryPoint;
	
	@Bean 
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(Collections.singletonList(authenticationProvider));
	}
	
	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilter() {
		JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
		filter.setAuthenticationManager(authenticationManager());
		filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
		return filter;
	}
	
	
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
			.authorizeRequests().antMatchers("/user/login").authenticated()
			.and()
			.exceptionHandling().authenticationEntryPoint(entryPoint)
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		
		http.headers().cacheControl();
		
		
		
		/* http.authorizeRequests()
         //These are public paths
         .antMatchers("/user/login").permitAll()
         //All remaining paths should need authentication.
         .anyRequest().fullyAuthenticated()
         .and()
         //logout will log the user out by invalidated session.
         .logout().permitAll()
         .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout", "POST"))
         .and()
         //login form and path
         .formLogin().loginPage("/user/login").and()
         //enable basic authentication
         .httpBasic().and()
         //We will handle it later.
         //Cross side request forgery
         .csrf().disable();*/
		
	}
	
	

}
