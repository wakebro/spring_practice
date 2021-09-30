package org.ict.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class ReplyVO {

	private Long rno;
	private Long bno;
	private String reply;
	private String replyer;
	private Date regdate;
	private Date updatedate;
}
