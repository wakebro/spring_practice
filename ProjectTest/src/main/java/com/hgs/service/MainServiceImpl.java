package com.hgs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hgs.domain.InterestVO;
import com.hgs.domain.MeetVO;
import com.hgs.domain.UserVO;
import com.hgs.mapper.MainMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@Service
@AllArgsConstructor
public class MainServiceImpl implements MainService {
	
	@Autowired
	private MainMapper mapper;

	@Override
	public UserVO get(String u_id) {
		log.info("user정보 조회");
		UserVO user = mapper.getUserInfo(u_id);
		return user;
	}
	
	@Override
	public List<InterestVO> get() {
		log.info("관심사 카테고리 조회");
		List<InterestVO> interestList = mapper.getInterest();
		return interestList;
	}

	@Override
	public List<MeetVO> getListMeet() {
		log.info("생성관 모임 리스트 조회");
		List<MeetVO> listMeet = mapper.getListMeet();
		return listMeet;
	}

}
