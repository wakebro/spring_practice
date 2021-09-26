package com.hgs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hgs.domain.InterestVO;
import com.hgs.domain.MeetVO;
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
		log.info("카테고리 조회");
		List<InterestVO> interestList = service.get();
		List<MeetVO> meetList = service.getListMeet();
		model.addAttribute("interest", interestList);
		model.addAttribute("meet", meetList);
	}
	
	@GetMapping("/register")
	public void register() {
	}
	@PostMapping("/register")
	public String register(RedirectAttributes rttr, MeetVO vo) {
		service.registerMeet(vo);
		return "redirect:/main/main";
	}
		

}
