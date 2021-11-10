package org.ict.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.ict.domain.AuthVO;
import org.ict.domain.CustomUser;
import org.ict.domain.MemberVO;
import org.ict.mapper.MemberMapper;
import org.ict.naver.NaverLoginBO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.github.scribejava.core.model.OAuth2AccessToken;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private NaverLoginBO naverLoginBO;
	@Autowired
	private MemberMapper service;
	
	private String apiResult = "";
	
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
	
	@GetMapping("/naverLogin")
	public String login(HttpSession session) {
		
		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		System.out.println("발급된 일회용 접근 URL 확인 : " + naverAuthUrl);
		// https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=*******&
		// redirect_uri=
		System.out.println("네이버 : " + naverAuthUrl);
		// 네이버 링크 세션에 저장
		session.setAttribute("url", naverAuthUrl);
		
		return "redirect:/customLogin";
	}
	
	@RequestMapping(value = "/naver/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session) throws IOException, ParseException, org.json.simple.parser.ParseException {
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		// 1. 로그인 사용자 정보를 읽어온다.
		apiResult = naverLoginBO.getUserProfile(oauthToken);	// String형식의 JSON데이터
		/*
		 * apiResult JSON구조 {"resultcode":"00", 
		 * 					"message"	:	"success",
		 * 					"response"	:	{"id"		:	"부여된 아이디",
		 * 									"nickname"	:	"닉네임",
		 * 									"age"		:	"나이대(10-19)",
		 * 									"gender"	:	"M, F",
		 * 									"email"		:	"이메일 주소",
		 * 									"name"		:	"유니코드명"}
		 * 					}
		*/
		// 2. String형식인 apiResult를 JSON 형태로 바꿈
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		
		JSONObject jsonObj = (JSONObject) obj;
		// 3. 데이터 파싱
		// TOP레벨 단계 _response 파싱
		JSONObject response_obj = (JSONObject) jsonObj.get("response");
		System.out.println("파싱해온 API : " + response_obj);
		// response의 nickname 값 파싱
		String id = (String) response_obj.get("id");
		String email = (String) response_obj.get("email");
		String userName = (String) response_obj.get("nickname");
		
		MemberVO user = new MemberVO();
		List<AuthVO> authList = new ArrayList<AuthVO>();
		AuthVO auth = new AuthVO();
		UUID uuid = UUID.randomUUID();
		auth.setUserid("NAVER_" + id);
		auth.setAuth("ROLE_MEMBER");
		authList.add(auth);
		
		user.setUserid("NAVER_"+id);
		user.setAuthList(authList);
		user.setUserpw(uuid.toString());
		user.setUserName(userName);
		System.out.println("INSERT하기 전 마지막 체크 : " + user);
		
		// DB에 해당 유저가 없을 경우 join
		if(service.read(user.getUserid()) == null) {
			service.insertMember(user);
		}
		CustomUser customUser = new CustomUser(user);
		
		// 시큐리티 권한을 직접 세팅함
		Authentication authentication = new UsernamePasswordAuthenticationToken(customUser, null, customUser.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "redirect:/secu/member";
	}
	
		
	
}
