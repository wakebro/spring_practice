package org.ict.mapper;

import java.util.List;

import org.ict.domain.*;

public interface BoardMapper {
	// board_tbl에서 글번호 3번 이하만 조회하는 쿼리문을
	// 어노테이션을 이용해 작성
//	@Select("SELECT * FROM board_tbl WHERE bno<4 ORDER BY bno DESC")
	// 글 검색
	public List<BoardVO> getList(String keyword);
	
	
	// INSERT구문 실행용으로 메서드 선언
	// VO내부에 적혀있는 정보를 이용해 INSERT를 한다.
	// BoardVO를 매개로 INSERT정보를 전달 받음
	public void insert(BoardVO vo);
	
	// insertSelectKey를 매퍼, 서비스, 컨트롤러에 적용
	public void insertSelectKey(BoardVO vo);
	
	// 글 번호(Long bno)를 파라미터로 받아
	// 해당 글 번호에 해당하는 글을 리턴해 보여주는 메서드 작성
	// 메서드 이름 select
	// xml 파일에 쿼리문 작성까지
	public BoardVO select(Long bno);
	
	
	
	// 글 번호(Long bno)를 파라미터로 받아
	// 해당 글 번호에 해당하는 글을 삭제해주는 메서드
	// xml 파일에 쿼리문 작성
	// 테스트코드까지 만들어 실제 삭제되는지 SQLdeveloper로 확인
	public void delete(Long bno);
	
	
	// 글 수정 로직 작성
	// BoardVO를 받아서 수정
	// 바꿀 내역은, title, content, writer는 vo에서 받고
	// updatedate는 sysdate로 수정
	// where구문은 bno로 구문
	public void update(BoardVO vo);
	
	
	// 페이징 처리를 하면서 조회할 것이므로
	// Criteria 정보를 파라미터로 제공해야
	// 몇 페이지의 글을 조회할지 정보를 같이 쿼리문에 전송할 수 있다.
	public List<BoardVO> getListPaging(Criteria cri);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
