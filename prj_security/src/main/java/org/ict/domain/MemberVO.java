package org.ict.domain;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class MemberVO {
	
	private String userid;
	private String userpw;
	private String userName;
	private boolean enabled;
	
	private Date regDate;
	private Date updateDate;
	
	// Join을 염두에 둔 VO형태를 List로 받는다
	// 하나의 계정에 두 개 이상의 권한이 들어가는 경우도 존재할 수 있으므로
	// List로 처리
	private List<AuthVO> authList;
}
