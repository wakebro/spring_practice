package org.ict.controller;

import java.util.List;

import org.ict.domain.BoardVO;
import org.ict.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

// 의존성, 로깅기능 설정
@Controller						// 컨트롤러이므로 컨트롤러로 빈컨테이너에 컴포넌트 스캔
@Log4j							// 로깅기능 추가
@RequestMapping("/board/*")		// 주소 기능_ 클래스 기능 위에 작성시 
							//이 클래스를 사용하는 모든 메서드의 연결주소 앞에 /board/ 추가
@AllArgsConstructor				// 의존성 주입 설정을 위해 생성자만 생성
public class BoardController {
	
	// 컨트롤러는 서비스를 호출 > 서비스는 mapper를 호출
	@Autowired
	private BoardService service;
	
	@GetMapping("/list")		// GET방식으로만 주소 연결. @RequestMapping으로 인해 /board/list가 됨
	public void list(Model model) {

		log.info("list로직 접속");
		// 전체 글 정보 얻어오기
		List<BoardVO> boardList = service.getList();
//		log.info(boardList);
		// view파일에 list라는 이름으로 넘겨주기
		model.addAttribute("list", boardList);
		
		// 1. views 하위에 경로에 맞는 폴더 및 .jsp 파일 생성
		// 2. 부트스트랩을 적용해 게시글 목록을 화면에 표시
	}
}
