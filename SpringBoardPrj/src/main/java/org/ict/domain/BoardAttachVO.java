package org.ict.domain;

import lombok.Data;

@Data
public class BoardAttachVO {
	private String uuid;
	private String uploadpath;
	private String fileName;
	private boolean image;
	
	private Long bno;
}
