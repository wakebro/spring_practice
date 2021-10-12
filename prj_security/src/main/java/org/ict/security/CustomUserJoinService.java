package org.ict.security;

import org.ict.domain.MemberVO;
import org.ict.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserJoinService {
	
	@Autowired
	private MemberMapper mapper;
	
	public void joinUser(MemberVO vo, String auth) {
		log.info("가입 유저 확인 : " + vo);
		log.info("가입 유저 권한 : " + auth);
		
//		mapper.insert(vo, auth);
	}

}
