package com.hgs.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class UserVO {
	private String u_id;
    private String u_pw;
    private String u_name;
    private int u_sex;
    private Date u_birth;
    private String u_area;
    private String u_intro;
    private String u_profile;
}
