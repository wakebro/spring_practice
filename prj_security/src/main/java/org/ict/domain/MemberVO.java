package org.ict.domain;

import java.sql.Date;
import java.util.List;

public class MemberVO {
	
	private String userid;
	private String userpw;
	private String userName;
	private boolean enabled;
	
	private Date regDate;
	private Date updateDate;
	private List<AuthVO> authList;
}
