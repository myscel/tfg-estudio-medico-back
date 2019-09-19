package com.example.tfgestudiomedico2019.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService UserDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
			.authorizeRequests()
			//These are public paths, don't authenticate these particular requests
			.antMatchers("/user/login").permitAll()
			
			//These can be reachable only for ADMIN role's users
			.antMatchers("/admin/**").hasRole("ADMIN")
			
			//All remaining paths should be authenticated
			.anyRequest().fullyAuthenticated()
			
			.and()
			//logout will log the user out by invalidating session.
			.logout().permitAll()
			.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout", "POST"))
			
			.and()
			//Login form and path
			.formLogin().loginPage("/user/login")
			
			.and()
			//CORS DISABLED
			.httpBasic().and()
			.csrf().disable();
	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {	
		auth.userDetailsService(this.UserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			 @Override
			 public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");

			 }
		};
	}
	

	
}
