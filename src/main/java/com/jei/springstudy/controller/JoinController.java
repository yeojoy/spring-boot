package com.jei.springstudy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class JoinController {
	
	static final Logger logger = LoggerFactory.getLogger(JoinController.class);
	
	@Autowired
	private DataSource dataSource;

	@RequestMapping(value = "/join")
	public String join() {
		logger.debug("landing join page.");
		return "joinForm";
	}
	
	@RequestMapping(value = "/join/newUser", method = RequestMethod.POST)
	public String joinSuccess(Model model, 
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password) {
		logger.debug("request create new user.");
		
		logger.debug("user id : " + username);
		
		JdbcUserDetailsManager userDetailsService = new JdbcUserDetailsManager();
		userDetailsService.setDataSource(dataSource);
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		
		if (!userDetailsService.userExists(username)) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			
			User userDetails = new User(username, encoder.encode(password), authorities);

			userDetailsService.createUser(userDetails);;
		} else {
			logger.debug(username + " already exists.");
			return "joinForm";
		}
		
		model.addAttribute("userName", username);
		return "joinSuccess";
		
	}
	
}
