package com.hgs.domain;

import lombok.Data;

@Data
public class MeetMemberVO {
	private String u_profile;
	private String u_id;
	private String u_name;
	private String u_intro;
	private int member_list_num;
	private Long m_num;
	private String member_list_position;
}
