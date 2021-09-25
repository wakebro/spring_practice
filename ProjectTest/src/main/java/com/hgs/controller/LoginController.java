package com.hgs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hgs.domain.UserVO;
import com.hgs.service.MainService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/login/*")
@AllArgsConstructor
@Log4j
public class LoginController {
	
	@Autowired
	private MainService service;
	
	@GetMapping("/process")
	public String login(RedirectAttributes rttr) {
		UserVO user = service.get("wake");
		log.info(user);
		rttr.addFlashAttribute("userInfo", user);
		
//		return "";
		return "redirect:/main/main";
	}
}
