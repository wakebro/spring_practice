package com.hgs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hgs.domain.MeetVO;
import com.hgs.service.MainService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/meet/*")
@Log4j
@AllArgsConstructor
public class MeetController {
	
	@Autowired
	private MainService service;
	
	@GetMapping("/info")
	public String detail(Model model, Long num) {
		MeetVO detail = service.getDetailMeet(num);
		log.info(detail);
		model.addAttribute("detail", detail);
		return "/meet/info";
	}
}
