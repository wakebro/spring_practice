package org.ict.controller;

import org.ict.domain.MemberVO;
import org.ict.security.CustomUserJoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/secu/*")
public class SecurityController {
	@Autowired
	CustomUserJoinService service;
	
	@GetMapping("/all")
	public void doAll() {
		log.info("모든 사람이 접속 가능한 all 로직");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
	@GetMapping("/member")
	public void doMember() {
		log.info("회원들이 접속 가능한 memeber 로직");
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/admin")
	public void doAdmin() {
		log.info("운영자만 접속 가능한 admin 로직");
	}
	
	@GetMapping("/join")
	public void join() {
		log.info("회원가입 로직");
	}
	
	@PostMapping("/join")
	public void joinPost(MemberVO vo, String auth) {
		log.info("컨트롤러 : " + vo);
		log.info("컨트롤러 : " + auth);
		
//		service.joinUser(vo, auth);
	}
}
