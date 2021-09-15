package org.ict.controller;

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
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
						"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
						// controller를 호출해야해서 위 내용을 둘 다 포함시킨다.
@Log4j
@WebAppConfiguration	// 웹사이트 모의접속용 어노테이션
public class BoardControllerTests {
	
	// 아래 나오는 MockMvc를 만들기 위해 생성하는 객체
	@Autowired
	private WebApplicationContext ctx;
	
	// 브라우저 없이 모의로 접속하는 기능을 가진 객체
	private MockMvc mockMvc;
	
	// @Test 이전에 실행할 내용을 기술하는 어노테이션
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testList() throws Exception {
		log.info(
				// .get(접속주소) / .post(접속주소)를 제외한 나머지는
				// 다 고정된 양식을 가진 코드이므로 복잡해보이지만
				// 실제로는 복사&붙여넣기를 사용해도 무방하다.
				// .get(접속주소)를 입력하면 get방식으로 해당 주소에 접속
				// /board/list에 접속하면 글 목록을 가져오는 페이지이기 때문에
				// 글 전체 목록을 가져오는지 여부를 테스트
				mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
				.andReturn()
				.getModelAndView()
				.getModelMap()
		);
	}
	
}
