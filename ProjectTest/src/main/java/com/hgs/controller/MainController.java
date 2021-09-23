package com.hgs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hgs.domain.InterestVO;
import com.hgs.domain.UserVO;
import com.hgs.service.MainService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/main/*")
@AllArgsConstructor
public class MainController {
	
	@Autowired
	private MainService service;
	
	@GetMapping("/main")
	public void list(Model model) {
		List<InterestVO> interestList = service.get();
		model.addAttribute("interest", interestList);
	}
	@GetMapping("/login")
	public String login(RedirectAttributes rttr) {
		UserVO user = service.get("wake");
		log.info(user);
		log.info("생일 : "+user.getU_birth());
		rttr.addFlashAttribute("userInfo", user);
		
		return "redirect:/main/main";
	}

}
