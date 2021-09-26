package com.hgs.service;

import java.util.List;

import com.hgs.domain.InterestVO;
import com.hgs.domain.MeetMemberVO;
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
	
	// 모임 디테일 가져오기
	public MeetVO getDetailMeet(Long m_num);
	
	// 모임 생성
	public void removeMeet(Long m_num);
	
	// 모임 수정
	public void updateMeet(MeetVO vo);
	
	// 모입 생성 시 방장 모임 가입
	public void adminJoinMeet(MeetMemberVO vo);
	
	// 모임 멤버 리스트 조회
	public List<MeetMemberVO> getMeetMemberList(Long num);
}
