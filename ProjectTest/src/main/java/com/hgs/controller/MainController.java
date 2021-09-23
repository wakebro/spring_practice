package com.hgs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/main/*")
@Log4j
public class MainController {
	
	@GetMapping("/main")
	public void list() {
		
	}
	@PostMapping("/user")
	public String user() {
		
		return "redirect:/main/main";
	}

}
