package org.ict.mapper;

import org.ict.domain.MemberVO;

public interface MemberMapper {
	
	public MemberVO read(String userid);
}
