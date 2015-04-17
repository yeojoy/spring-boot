package com.jei.springstudy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jei.springstudy.model.Friends;
import com.jei.springstudy.repository.FriendsRepository;

@Controller
public class IndexController {
	
	static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private FriendsRepository friendsRepo;
	
	@RequestMapping(value = "/")
	public String root() {
		return sayHello();
	}
	
	@RequestMapping(value = "/index")
	public String sayHello() {
		logger.debug("landing index page.");
		return "index";
	}

	@RequestMapping(value = "/greeting")
	public String greetToSomeoneByName(Model model,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "userId", required = false) String userId) {
		
		logger.debug("landing greeting page.");
		
		if (name != null && !name.isEmpty()) {
			logger.debug("user name is " + name + ".");
			model.addAttribute("name", name);
		} else if (userId != null && !userId.isEmpty()) {
			logger.debug("user id is " + userId + ".");
			Friends aFriend = friendsRepo.findByUserId(userId);
			if (aFriend == null) {
				aFriend = new Friends();
				aFriend.setName("Anonymous");
			}
			
			logger.debug("user name is " + aFriend.getName() + ".");
			model.addAttribute("name", aFriend.getName());	
		} else {
			logger.debug("No params.");
			model.addAttribute("name", "Anonymous!!!");			
		}
		return "greeting";
	}
	
	
	@RequestMapping(value = "/sayhello")
	public String sayHelloToFriend(Model model, @Param("userId") String userId) {
		
		Friends aFriend = friendsRepo.findByUserId(userId);
		if (aFriend == null) {
			aFriend = new Friends();
			aFriend.setName("Anonymous");
		}
		
		model.addAttribute("friend", aFriend);
		return "sayHello";
	}
}
