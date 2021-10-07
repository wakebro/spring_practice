package org.ict.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ict.domain.Criteria;
import org.ict.domain.ReplyVO;

public interface ReplyMapper {

	public List<ReplyVO> getList(Long bno);
	
	public void create(ReplyVO vo);

	public void update(ReplyVO vo);
	
	public void delete(Long bno);
	
	public List<ReplyVO> getListWithPaging(
			@Param("cri") Criteria cri,
			@Param("bno") Long bno);
	
	public Long getBno(Long rno);
}
