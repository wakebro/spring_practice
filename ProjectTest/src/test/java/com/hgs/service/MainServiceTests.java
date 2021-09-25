package com.hgs.service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hgs.service.MainService;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MainServiceTests {
	
	@Autowired
	private MainService service;

	@Test
	// 생성된 모임 리스트 조회
	public void testGetListMeet() {
		log.info(service.getListMeet());
	}
}
