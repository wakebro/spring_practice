package org.ict.service;

import java.util.List;

import org.ict.domain.Criteria;
import org.ict.domain.ReplyVO;
import org.ict.mapper.BoardMapper;
import org.ict.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class ReplyServicImpl implements ReplyService {
	
	@Autowired
	private ReplyMapper mapper;
	
	// 리플 썼을 때 board_tbl도 업데이트 해야하므로 board_tbl에 접근할 수 있는
	// BoardMapper도 같이 호출할 수 있게 생성
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<ReplyVO> listReply(Long bno) {
		List<ReplyVO> list = mapper.getList(bno);
		return list;
	}
	
	@Transactional
	@Override
	public void addReply(ReplyVO vo) {
		log.info("Reply : " + vo.getBno());
		mapper.create(vo);
		// 글 쓰고 나서, board_tbl쪽 게시판 댓글에 1 추가
		boardMapper.updateReplyCnt(vo.getBno(), 1L);
	}

	@Override
	public void modifyReply(ReplyVO vo) {
		mapper.update(vo);
	}

	@Transactional
	@Override
	public void removeReply(Long rno) {
		// mapper.delete가 실해오디는 순간, bno를 포함한 모든 rno번 row가 날아감
		// 먼저 bno를 얻어서 저장 후, rno번 row를 삭제해야한다.
		Long bno = mapper.getBno(rno);
		mapper.delete(rno);
		boardMapper.updateReplyCnt(bno, -1L);
	}
	
	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		return mapper.getListWithPaging(cri, bno);
	}
	
}
