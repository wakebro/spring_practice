package org.ict.mapper;

import org.apache.ibatis.annotations.Select;

public interface BoardMapper {
	// board_tbl에서 글번호 3번 이하만 조회하는 쿼리문을
	// 어노테이션을 이용해 작성
	@Select("SELECT * FROM board_tbl ORDER BY bno < 3 DESC")
	public String getAll();
}
