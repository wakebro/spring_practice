package org.ict.mapper;

import java.util.List;

import org.ict.domain.Criteria;
import org.ict.domain.ReplyVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMatterTests {

	@Autowired
	private ReplyMapper mapper;
	
//	@Test
	public void testGetList() {
		log.info(mapper.getList(31768L));
	}
	
//	@Test
	public void testCreate() {
		ReplyVO vo = new ReplyVO();
		vo.setBno(31768L);
		vo.setReply("좋아요");
		vo.setReplyer("유저");
		mapper.create(vo);
	}
	
//	@Test
	public void testUpdate() {
		ReplyVO vo = new ReplyVO();
		vo.setReply("수정 댓글");
		vo.setRno(21L);
		mapper.update(vo);
	}
	
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		List<ReplyVO> replies = mapper.getListWithPaging(cri, 81921L);
		log.info(replies);
		
	}
}
