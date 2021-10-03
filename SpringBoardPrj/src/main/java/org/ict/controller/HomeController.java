package org.ict.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@Controller
@Log4j
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	// 세션 추가를 위해서 컨트롤러 내부 메서드마다 설정을 해주어야 한다_비효율적
	// HttpServletRequest 타입 변수 선언하고
	// 내부에 .getSession() 명령어로 세션을 받아와 사용
	@GetMapping("/session1")
	public String se1(HttpServletRequest request) {
		// 위와 같이 HttpServletRequest 객체를 파라미터에 선언 후
		// HttpSession 객체를 얻어오면 세션기능을 그대로 사용 가능하다.
		HttpSession session = request.getSession();
		
		session.setAttribute("sTest", "123");
		
		return "sesseion1";
	}
	
	@GetMapping("/session2")
	public String se2(HttpServletRequest request) {
		// 위와 같이 HttpServletRequest 객체를 파라미터로 선언한 후,
		// HttpSession 객체를 얻어오면 세션 기능을 쓸 수 있다.
		HttpSession session = request.getSession();
		
		log.info("세션작동확인 : " + session.getAttribute("sTest"));
		
		return "";
	}
	
	@GetMapping("/test")
	public void ajaxTest() {
		
	}
	
}
