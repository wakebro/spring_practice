package org.ict.mapper;

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
	@Test
	public void testGetList() {
		// mapper 내부의 getList 메서드를 호출
		log.info("Oracle 조회중...");
		log.info(boardMapper.getList());
	}
}
