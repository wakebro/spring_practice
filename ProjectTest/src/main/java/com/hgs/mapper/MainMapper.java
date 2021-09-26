package com.hgs.mapper;

import java.util.List;

import com.hgs.domain.InterestVO;
import com.hgs.domain.MeetVO;
import com.hgs.domain.UserVO;

public interface MainMapper {
	
	// 유저 정보
	public UserVO getUserInfo(String u_id);
	
	// 관심사 카테고리
	public List<InterestVO> getInterest();
	
	// 모임 출력
	public List<MeetVO> getListMeet();
	
	// 모임 1개 디테일 출력
	public MeetVO getDetailMeet(Long m_num);
	
	// 모임 생성
	public void insertMeet(MeetVO vo);
	
	// 모임 삭제
	public void removeMeet(Long m_num);
	
	// 모임 수정
	public void updateMeet(MeetVO vo);
}
