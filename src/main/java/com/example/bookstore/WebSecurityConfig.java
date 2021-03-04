package com.example.bookstore;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
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

// Creating an object based on UserDetailsService class,
// which creates the roles for regular user and admin
// for the application.
// NB: IN THIS EXERCISE, THE ROLES ARE CREATED AS IN-MEMORY,
// WHICH MEANS THAT THEY DO NOT EXIST IN A DATABASE OR ANYWHERE
// OUTSIDE THE USER'S DEVICE

@Bean
@Override
public UserDetailsService userDetailsService() {
	List<UserDetails> users = new ArrayList();
	
	PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	
	UserDetails user = User
			.withUsername("user")
			.password(passwordEncoder.encode("user"))
			.roles("USER")
			.build();
	
	users.add(user);
	
	user = User
			.withUsername("admin")
			.password(passwordEncoder.encode("admin"))
			.roles("USER", "ADMIN")
			.build();
	
	users.add(user);
	
	return new InMemoryUserDetailsManager(users);
}
	
}