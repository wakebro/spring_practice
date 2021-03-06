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
	// 주의! org.junit.Before 사용!
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
//	@Test
	public void testList() throws Exception {
		log.info(
				// .get(접속주소) / .post(접속주소)를 제외한 나머지는
				// 다 고정된 양식을 가진 코드이므로 복잡해보이지만
				// 실제로는 복사&붙여넣기를 사용해도 무방하다.
				// .getㅅ(접속주소)를 입력하면 get방식으로 해당 주소에 접속
				// /board/list에 접속하면 글 목록을 가져오는 페이지이기 때문에
				// 글 전체 목록을 가져오는지 여부를 테스트
				mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
				.andReturn()
				.getModelAndView()
				.getModelMap()
		);
	}
	
	
	// /board/register 주소로 파라미터값을 post방식으로 넘겼을 때
	// 글이 써지는지 안 써지는지 확인
//	@Test
	public void testRegister() throws Exception {
		
		// 아래 코드는 post방식으로 파라미터 3개를 주소에 전달해주는 코드이다.
		// 결과 메세지는 문자열 resultPage에 저장
		String resultPage = mockMvc.perform(
				MockMvcRequestBuilders.post("/board/register")
				.param("title", "테스트코드제목")
				.param("content", "테스트코드본문")
				.param("writer", "테스트코드글쓴이"))
				.andReturn()
				.getModelAndView()
				.getViewName();
		
		// 변수에 저장된 값을 다시 로깅하여 출력
		log.info(resultPage);
	}
	
	
	// .param("bno", "글번호")로 파라미터를 줬을 때
	// 해당 글이 잘 얻어와지는지 체크
	// .param()으로 전달하는 자료는 자료형을 막론하고 무조건
	// " "로 감싸서 문자화 시켜야하는데 이유는
	// url에는 자료형 구분없이 오직 String뿐이기 때문이다.
//	@Test
	public void testGet() throws Exception {
		String getPage = mockMvc.perform(
				MockMvcRequestBuilders.get("/board/get")
				.param("bno", "14"))
				.andReturn()
				.getModelAndView()
				.getViewName();
		
		log.info(getPage);
	}
	
	
//	@Test
	public void testRemove() throws Exception {
		String remove = mockMvc.perform(
				MockMvcRequestBuilders.post("/board/remove")
				.param("bno", "1"))
				.andReturn()
				.getModelAndView()
				.getViewName();
		log.info(remove);
	}
	
	
//	@Test
	public void testModify() throws Exception {
		
		// 실제로 실행될 쿼리문과 비교해서 데이터를 전송
		// 현재 수정로직은 bno를 WHERE절의 조건으로
		// title, conetnt, writer를 수정내역으로 받아
		// 파라미터를 위 4개 항목을 전달해준다
		
		String modify = mockMvc.perform(
				MockMvcRequestBuilders.post("/board/modify")
				.param("bno", "5")
				.param("title", "테스트코드수정제목")
				.param("content", "테스트코드수정본문")
				.param("writer", "테스트코드수정글쓴이"))
				.andReturn()
				.getModelAndView()
				.getViewName();
		log.info(modify);
	}
	
//	@Test
	public void testBoardModify() throws Exception{
		String boardModify = mockMvc.perform(
				MockMvcRequestBuilders.post("/board/boardmodify")
				.param("bno", "5"))
				.andReturn()
				.getModelAndView()
				.getViewName();
		log.info(boardModify);
	}
	
//	@Test
	public void testListPaging() throws Exception{
		String resultPage = mockMvc.perform(
				MockMvcRequestBuilders.get("/board/list")
				.param("pageNum", "258")
				.param("amount", "15"))
				.andReturn()
				.getModelAndView()
				.getViewName();
		log.info(resultPage);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
