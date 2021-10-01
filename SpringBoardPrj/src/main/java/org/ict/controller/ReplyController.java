package org.ict.controller;

import java.util.List;

import org.ict.domain.ReplyVO;
import org.ict.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/replies")
public class ReplyController {

	@Autowired
	private ReplyService service;
	
	// consumes는 해당 메서드의 파라미터를 넘겨줄 때 어떤 형식으로 넘겨줄지를
	// 설정하는데, 기본적으로 REST방식에는 JSON을 사용
	// produces는 입력받은 데이터를 토대로 로직을 실행한 후
	// 사용자에게 결과로 보여줄 데이터 형식을 나타낸다
	@PostMapping(value = "", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	// produces에 TEXT_PLAIN_VALUE를 설정했으므로 결과코드와 문자열을 넘김
	public ResponseEntity<String> register(@RequestBody ReplyVO vo){
									// REST 컨트롤러에서 받는 파라미터 앞에
									// @RequestBody 어노테이션이 붙어야
									// consumes와 연결됨
			
		// 깡통 entity를 생성
		ResponseEntity<String> entity = null;
		try {
			// 먼저 글쓰기 로직 실행후 에러가 없다면 아래 내용 실행&저장
			service.addReply(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			// 글쓰기 로직에 문제가 생긴 경우 아래 내욜 실행&저장
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		// 위 try나 catch에서 얻어온 entity 변수를 리턴
		return entity;
	}
	
	// 단일 숫자데이터 bno만 넣기도 하고
	// PathVariable 어노테이션으로 이미 입력데이터가
	// 명시되어 consumes는 따로 설정하지 않아도 됨
	// produces는 댓글 목록이 XML로도, JSON으로도 표현될 수 있도록
	// 아래와 같이 2개를 모두 설정
	@GetMapping(value = "/all/{bno}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") Long bno){
		ResponseEntity<List<ReplyVO>> entity = null;
		try {
			entity = new ResponseEntity<>(service.listReply(bno), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	// 일반 방식이 아닌 REST방식에는 삭제로직을 POST가 아닌
	// DELETE 방식으로 요청하기 때문에 @DeleteMapping을 사용한다.
	@DeleteMapping(value = "/{rno}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
		ResponseEntity<String> entity = null;
		try {
			service.removeReply(rno);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
