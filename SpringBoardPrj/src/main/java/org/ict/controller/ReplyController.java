package org.ict.controller;

import org.ict.domain.ReplyVO;
import org.ict.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
}
