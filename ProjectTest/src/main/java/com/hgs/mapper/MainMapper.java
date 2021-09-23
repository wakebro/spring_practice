package com.hgs.mapper;

import java.util.List;

import com.hgs.domain.InterestVO;
import com.hgs.domain.UserVO;

public interface MainMapper {
	
	// 유저 정보
	public UserVO getUserInfo(String u_id);
	
	// 관심사 카테고리
	public List<InterestVO> getInterest();
}
