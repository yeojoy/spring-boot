package com.jei.springstudy.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping(value = "/")
	public String sayHello() {
		
		return "index";
	}

	@RequestMapping(value = "/greeting")
	
	public String greetToSomeone(Model model, @Param("name") String name) {
		
		model.addAttribute("name", name);
		return "greeting";
	}
}
