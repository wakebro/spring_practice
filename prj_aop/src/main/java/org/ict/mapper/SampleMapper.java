package org.ict.mapper;

import org.apache.ibatis.annotations.Insert;

public interface SampleMapper {
	
	@Insert("insert into tbl_test1(col1) values(#{data})")
	public int insertCol1(String data);
}
