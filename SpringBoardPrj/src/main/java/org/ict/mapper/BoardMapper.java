package org.ict.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.ict.domain.*;

public interface BoardMapper {
	// board_tbl에서 글번호 3번 이하만 조회하는 쿼리문을
	// 어노테이션을 이용해 작성
//	@Select("SELECT * FROM board_tbl WHERE bno<4 ORDER BY bno DESC")
	public List<BoardVO> getList();
	
	
	// INSERT구문 실행용으로 메서드 선언
	// VO내부에 적혀있는 정보를 이용해 INSERT를 한다.
	// BoardVO를 매개로 INSERT정보를 전달 받음
	public void insert(BoardVO vo);
}
