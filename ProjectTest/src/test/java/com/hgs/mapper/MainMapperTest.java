package com.hgs.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MainMapperTest {
	@Autowired
	private MainMapper mainMapper;
	
	@Test
	public void testGetUserInfo() {
		log.info("Oracle 조회중...");
		String u_id = "wake";
		log.info(mainMapper.getUserInfo(u_id));
	}
}
