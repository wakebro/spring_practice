package com.hgs.mapper;

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
public class MainMapperTest {
	@Autowired
	private MainMapper mainMapper;
	
//	@Test
	// 유저 정보 가져오기
	public void testGetUserInfo() {
		log.info("Oracle 조회중...");
		String u_id = "wake";
		log.info(mainMapper.getUserInfo(u_id));
	}
	
//	@Test
	// 관심사 카테고리 가져오기
	public void testGetInterestCategory() {
		log.info("Oracle 조회중");
		log.info(mainMapper.getInterest());
	}
	
//	@Test
	public void testGetListMeet() {
		log.info(mainMapper.getListMeet());
	}
	
//	@Test
	public void testInsertMeet() {
		MeetVO vo = new MeetVO();
		log.info("모임 생성중");
		vo.setM_name("러닝 메이트");
		vo.setM_content("러닝 메이트 구합니다. 같이 뛰어요~");
		vo.setM_people_cnt(10);
		vo.setM_area("종로구");
		vo.setM_profile("");
		vo.setI_cate_num(1);
		vo.setU_id("wake");
		
		mainMapper.insertMeet(vo);
	}
	
//	@Test
	// 모임 디테일 가져오기
	public void testGetDetailMeet() {
		log.info(mainMapper.getDetailMeet(6L));
	}
	
//	@Test
	// 모임 삭제
	public void testRemoveMeet() {
		mainMapper.removeMeet(7L);
	}
	
	@Test
	// 모임 수정
	public void testUpdateMeet() {
		MeetVO vo = new MeetVO();
		log.info("모임 수정중");
		vo.setM_num(0L);
		vo.setM_name("한강 러닝_수정");
		vo.setM_content("러닝 메이트 수정합니다");
		vo.setM_people_cnt(15);
		vo.setM_area("구로구");
		vo.setM_profile("");
		vo.setI_cate_num(1);
		vo.setU_id("wake");
		mainMapper.updateMeet(vo);
	}
}
