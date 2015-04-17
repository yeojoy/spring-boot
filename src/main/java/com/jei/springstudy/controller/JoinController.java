package com.jei.springstudy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jei.springstudy.repository.FriendsRepository;

@Controller
@RequestMapping(value = "/join")
public class JoinController {
	
	static final Logger logger = LoggerFactory.getLogger(JoinController.class);
	
	@Autowired
	private FriendsRepository friendsRepo;
	
	public String join() {
		logger.debug("landing join page.");
		return "index";
	}

	
}
