package com.hgs.service;

import java.util.List;

import com.hgs.domain.InterestVO;
import com.hgs.domain.MeetVO;
import com.hgs.domain.UserVO;

public interface MainService {
	// 임시_사용자 정보 가져오기
	public UserVO get(String u_id);
	
	// 관심사 카테고리 가져오기
	public List<InterestVO> get();
	
	// 생성된 모임 리스트 가져오기
	public List<MeetVO> getListMeet();
	
	// 모임 생성
	public void registerMeet(MeetVO vo);
}
