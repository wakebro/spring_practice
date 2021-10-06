package org.ict.mapper;

import org.apache.ibatis.annotations.Insert;


public interface SampleMapper2 {

	@Insert("insert into tbl_test2(col1) values(#{col1})")
	public int insertCol1(String col1);
}
