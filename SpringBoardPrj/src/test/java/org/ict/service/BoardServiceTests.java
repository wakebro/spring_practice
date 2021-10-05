package org.ict.service;

import static org.junit.Assert.assertNotNull;

import org.ict.domain.BoardVO;
import org.ict.domain.Criteria;
import org.ict.domain.SearchCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

// Service테스트는 BoardServiceImpl 내부 기능을
// 서버 가동 없이 테스트하기 위해 작성
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	
	// 다형성의 원리에 의해서 BoardService로 만들어도
	// BoardServiceImpl이 주입됨
	@Autowired
	private BoardService service;
	
	
	// 서비스가 제대로 주입되었는지 여부 확인
//	@Test
	public void testExist() {
		log.info(service);
		// assertNotNull은 해당 객체가 주입이 되지 않아
		// null인 경우 에러를 발생
		assertNotNull(service);
	}
	
//	@Test
	public void testRegister() {
		// register로직이 BoardVO를 필요로하므로
		// 먼저 vo부터 생성하여 자료 입력 후 전달
		BoardVO vo = new BoardVO();
		vo.setTitle("Service Test");
		vo.setContent("I'm doing Service Test");
		vo.setWriter("User");
		service.register(vo);
	}
	
	
//	@Test
	public void testGetList() {
		String keword = "";
		service.getList(keword);
	}
	
//	@Test
	public void testGet() {
		service.get(8L);
	}
	
//	@Test
	public void testRemove() {
		service.remove(4L);
	}
	
//	@Test
	public void testModify() {
		// 수정사항 정보를 BoardVO에 담아
		// 전달하기 때문에 BoardVO를 만들어놓고 저장한 뒤 실행
		BoardVO vo = new BoardVO();
		
		vo.setBno(5L);
		vo.setTitle("서비스 수정");
		vo.setContent("서비스 수정내용입니다.");
		vo.setWriter("유저2_수정");
		service.modify(vo);
	}
	
//	@Test
	public void testListPaging() {
		SearchCriteria cri = new SearchCriteria();
		service.getListPaging(cri);
	}
	
	@Test
	public void testGetTotalBoardCnt() {
//		int totalCnt = service.getTotalBoardCnt();
//		log.info(totalCnt);
	}
	
}
