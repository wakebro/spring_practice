package org.ict.service;

import java.util.List;

import org.ict.domain.BoardVO;
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
	
	
	@Override
	public void register(BoardVO vo) {
		mapper.insert(vo);
		// TODO Auto-generated method stub
		
	}

	@Override
	public BoardVO get(Long bno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modify(BoardVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Long bno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BoardVO> getList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
