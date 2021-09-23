package org.ict.mapper;

import org.ict.domain.BoardVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

// 테스트코드 기본세팅(RunWith, ContextConfiguration, Log4j)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	// 이 테스트코드 내에서는 Mapper를 담당
	// 따라서 BoardMapper내부의 메서드를 실행할 예정이고
	// BoardMapper 타입의 변수가 필요해
	// 선언 후 자동주입이 필요
	@Autowired
	private BoardMapper boardMapper;
	
	// 테스트용 메서드 이름 testGetList
	// 테스트 코드가 실행되도록 로직 작성
//	@Test
	public void testGetList() {
		// mapper 내부의 getList 메서드를 호출
		log.info("Oracle 조회중...");
		String keyword = "";
		log.info(boardMapper.getList(keyword));
	}
	
	// INSERT를 실행할 테스트코드 작성
//	@Test
	public void testInsert() {
		// 글 입력을 위해서 BoardVO 타입을 매개로 사용
		// 따라서 BoardVO를 만들어놓고, setter로 글제목, 본문, 글쓴이를
		// 저장해둔 채로 mapper.insert(vo);를 호출하여 실행여부 확인
		// 위 설명을 토대로 아래 vo에 6번글에 대한 제목, 본문, 글쓴이를 넣고
		// 호출하여 DB에 들어갔는지 확인
		BoardVO vo = new BoardVO();
		vo.setTitle("스프링Insert2");
		vo.setContent("스프링테스트 본문내용2");
		vo.setWriter("스프링유저");
		boardMapper.insert(vo);
	}
	
//	@Test
	public void testSelect() {
		boardMapper.select(5L);
	}
	
	
//	@Test
	public void testDelete() {
		boardMapper.delete(3L);
	}
	
	
	@Test
	public void testupdate() {
		BoardVO vo = new BoardVO();
		vo.setBno(1L);
		vo.setTitle("테스트글1 수정");
		vo.setContent("테스트1본문_수정");
		vo.setWriter("유저1_수정");
		boardMapper.update(vo);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
