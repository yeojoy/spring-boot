package com.jei.springstudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jei.springstudy.model.Friends;
import com.jei.springstudy.repository.FriendsRepository;

@Controller
public class IndexController {
	
	@Autowired
	private FriendsRepository friendsRepo;
	
	@RequestMapping(value = "/")
	public String sayHello() {
		
		return "index";
	}

	@RequestMapping(value = "/greeting")
	public String greetToSomeone(Model model, @Param("name") String name) {
		
		model.addAttribute("name", name);
		return "greeting";
	}
	
	@RequestMapping(value = "/sayhello")
	public String sayHelloToFriend(Model model, @Param("name") String name) {
		
		Friends aFriend = friendsRepo.findByName(name);
		if (aFriend == null) {
			aFriend = new Friends();
			aFriend.setName("Anonymous");
			
		}
		model.addAttribute("friend", aFriend);
		return "sayHello";
	}
}
