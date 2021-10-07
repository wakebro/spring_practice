package org.ict.service;

import java.util.List;

import org.ict.domain.Criteria;
import org.ict.domain.ReplyVO;
import org.ict.mapper.BoardMapper;
import org.ict.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
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
	
	@Override
	public void addReply(ReplyVO vo) {
		mapper.create(vo);
		// 글 쓰고 나서, board_tbl쪽 게시판 댓글에 1 추가
		boardMapper.updateReplyCnt(vo.getRno(), 1L);
	}

	@Override
	public void modifyReply(ReplyVO vo) {
		mapper.update(vo);
	}

	@Override
	public void removeReply(Long rno) {
		mapper.delete(rno);
		Long bno = mapper.getBno(rno);
		boardMapper.updateReplyCnt(bno, -1L);
	}
	
	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		return mapper.getListWithPaging(cri, bno);
	}
	
}
