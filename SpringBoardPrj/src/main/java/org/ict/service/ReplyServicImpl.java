package org.ict.service;

import java.util.List;

import org.ict.domain.ReplyVO;
import org.ict.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReplyServicImpl implements ReplyService {
	
	@Autowired
	private ReplyMapper mapper;
	
	@Override
	public List<ReplyVO> listReply(Long bno) {
		List<ReplyVO> list = mapper.getList(bno);
		return list;
	}
	
	@Override
	public void addReply(ReplyVO vo) {
		mapper.create(vo);
	}

	@Override
	public void modifyReply(ReplyVO vo) {
		mapper.update(vo);
	}

	@Override
	public void removeReply(Long rno) {
		mapper.delete(rno);
	}
	
}
