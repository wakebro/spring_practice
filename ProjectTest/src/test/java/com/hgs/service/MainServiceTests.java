package com.hgs.service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hgs.domain.MeetVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MainServiceTests {
	
	@Autowired
	private MainService service;

//	@Test
	// 생성된 모임 리스트 조회
	public void testGetListMeet() {
		log.info(service.getListMeet());
	}
	
	// 모임 생성
	@Test
	public void testRegisterMeet() {
		MeetVO vo = new MeetVO();
		log.info("모임 생성중...");
		vo.setM_name("홍대 방송댄스");
		vo.setM_content("취미로 방송댄스 하실분 모집! 초보 환영~");
		vo.setM_people_cnt(20);
		vo.setM_area("마포구");
		vo.setM_profile("");
		vo.setI_cate_num(8);
		vo.setU_id("wake");
		service.registerMeet(vo);
	}
}
