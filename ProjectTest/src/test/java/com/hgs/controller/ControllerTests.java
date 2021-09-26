package com.hgs.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
						"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
@WebAppConfiguration
public class ControllerTests {
	
	@Autowired
	private WebApplicationContext ctx;
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
//	@Test
	// 로그인 유저정보
	public void testUserInfo() throws Exception{
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/login/process"))
				.andReturn()
				.getModelAndView()
				.getViewName()
				);
	}
	
//	@Test
	// 메인화면 관심사 카테고리, 모임 리스트
	public void testMainPage() throws Exception {
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/main/main"))
				.andReturn()
				.getModelAndView()
				.getViewName()
				);
	}
	
//	@Test
	// 모임 생성
	public void testRegisterMeet() throws Exception {
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.post("/main/register")
				.param("m_name", "야구관람")
				.param("m_content", "야구보러 값시다~!")
				.param("m_people_cnt", "30")
				.param("m_area", "서초구")
				.param("m_profile", "")
				.param("i_cate_num", "12")
				.param("u_id", "wake"))
				.andReturn()
				.getModelAndView()
				.getViewName()
				);
	}
	
//	@Test
	// 모임 조회
	public void testDetailMeet() throws Exception {
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/meet/info")
				.param("num","2"))
				.andReturn()
				.getModelAndView()
				.getViewName()
				);

	}
	
//	@Test
	// 모임 삭제
	public void testRemoveMeet() throws Exception {
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.post("/meet/remove")
				.param("num","9"))
				.andReturn()
				.getModelAndView()
				.getViewName()
				);
	}
	
	@Test
	// 모임 수정
	public void testUpdateMeet() throws Exception{
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.post("/meet/update")
				.param("m_num", "6")
				.param("m_name", "가죽공예")
				.param("m_content", "악세사리 만드는 가죽공예 모임입니다.")
				.param("m_people_cnt", "25")
				.param("m_area", "강서구")
				.param("m_profile", "")
				.param("i_cate_num", "6")
				.param("u_id", "wake"))
				.andReturn()
				.getModelAndView()
				.getViewName()
				);
	}
}
