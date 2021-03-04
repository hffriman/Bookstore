package com.example.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.bookstore.web.UserDetailServiceImpl;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailServiceImpl userDetailsService;

	// I FORGOT TO COMMENT THIS SECTION IN PREVIOUS EXERCISE:
	// Creating a void function which defines
	// which pages are either accessible or
	// immediately loaded when the application starts
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http
		.authorizeRequests().antMatchers("/css/**").permitAll()
		.and()
		.authorizeRequests().anyRequest().authenticated()
		.and()
	.formLogin()
		.defaultSuccessUrl("/booklist",true)	
		.permitAll()
		.and()
	.logout()
		.permitAll();
}

// Creating an void function that uses
// the AuthenticationManagerBuilder to 
// call the userDetailsService object,
// thus creating a new passwordEncoder object
// to encrypt the passwords in the application
@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
}
	
}