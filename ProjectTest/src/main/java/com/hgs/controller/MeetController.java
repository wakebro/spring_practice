package com.hgs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	// 모임 상세 정보
	public String detail(Model model, Long num) {
		MeetVO detail = service.getDetailMeet(num);
		log.info(detail);
		model.addAttribute("detail", detail);
		return "/meet/info";
	}
	
	@PostMapping("/remove")
	// 모임 삭제
	public String remove(Long num) {
		service.removeMeet(num);
		return "redirect:/main/main";
	}
	
	@PostMapping("/update/process")
	// 모임 수정
	public String updateMeet(MeetVO vo) {
		service.updateMeet(vo);
		return "redirect:/meet/info?num=" + vo.getM_num();
	}
	
	@PostMapping("/update")
	public void updateMeet(Long num, Model model) {
		MeetVO vo = service.getDetailMeet(num);
		model.addAttribute("vo", vo);
	}
}
