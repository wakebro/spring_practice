package org.ict.mapper;

import org.apache.ibatis.annotations.Param;
import org.ict.domain.MemberVO;

public interface MemberMapper {
	
	public MemberVO read(String userid);
	
	public void insert(@Param("vo")MemberVO vo, @Param("auth")String auth);	
}
