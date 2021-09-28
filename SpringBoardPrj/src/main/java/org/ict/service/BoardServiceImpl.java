package org.ict.service;

import java.util.List;

import org.ict.domain.BoardVO;
import org.ict.domain.SearchCriteria;
import org.ict.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

// BoardServiceImpl은 BoardService 인터페이스를 구현한다.
@Log4j 	// 로깅을 위한 어노테이션 // x가 뜨면 pom.xml에서 추가수정_버전 수정1.2.17
		// <exclusions> 내용 삭제,
		// <scope> 주석처리
@Service // 의존성 등록을 위한 어노테이션
@AllArgsConstructor // 서비스 생성자 자동생성
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper mapper;
	
	
	// 등록작업시 BoardVO를 매개로 실행하기 때문에
	// 아래와 같이 BoardVO를 파라미터에 설정
	// BoardServiceTests에 VO를 생성하여 테스트 실행
	@Override
	public void register(BoardVO vo) {
		log.info("등록 작업 실행");
		// mapper.isnert(vo); 에서 bno를 얻기위해 변경
		mapper.insertSelectKey(vo);
		
	}

	
	// 전체 글을 다 가져오는게 아닌, 특정 글 하나만 가져오는 로직 작성
	@Override
	public BoardVO get(Long bno) {
		BoardVO result = mapper.select(bno);
		log.info(bno + "번 글 조회");
		return result;
	}
	

	@Override
	public void modify(BoardVO vo) {
		log.info("수정 작업 진행 - " + vo);
		mapper.update(vo);
	}

	@Override
	public void remove(Long bno) {
		log.info(bno + "번 글 삭제 작업 진행");
		mapper.delete(bno);
	}

	
	// 글 전체 목록을 가져오는 로직 작성
	// 해당 로직은 mapper 내부의 getList의 쿼리문을 먼저
	// 전체 글을 가져오는 로직으로 수정 후, service에 등록하여 구현
	@Override
	public List<BoardVO> getList(String keyword) {
		log.info("BoardVO 리스트 조회");
		List<BoardVO> boardList =  mapper.getList(keyword);
		return boardList;
	}
	
	
	// 글 페이징 목록
	@Override
	public List<BoardVO> getListPaging(SearchCriteria cri) {
		log.info("글 페이징 목록 조회");
		// cri 정보(pageNum, amount)를 받아와
		// mapper쪽의 getListPaging 호출 후
		// 나온 결과물을 리턴하여 컨트롤러에서 쓸 수 있도록 처리
		List<BoardVO> boardList = mapper.getListPaging(cri);
		return boardList;
	}
	
	@Override
	public int getTotalBoardCnt(SearchCriteria cri) {
		
		return mapper.getTotalBoardCnt(cri);
	}
	
	
}
