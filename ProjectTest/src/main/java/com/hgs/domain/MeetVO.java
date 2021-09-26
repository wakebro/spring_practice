package com.hgs.domain;

import lombok.Data;

@Data
public class MeetVO {
	
    private Long m_num;
    private String m_name;
    private String m_content;
    private int m_people_cnt;
    private String m_area;
    private String m_profile;
    private int i_cate_num;
    private String i_cate_name;
    private String u_id;
}
