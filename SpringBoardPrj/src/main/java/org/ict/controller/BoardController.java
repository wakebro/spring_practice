package org.ict.controller;

import java.util.List;

import org.ict.domain.BoardAttachVO;
import org.ict.domain.BoardVO;
import org.ict.domain.PageDTO;
import org.ict.domain.SearchCriteria;
import org.ict.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	/*
	@GetMapping("/list")		// GET방식으로만 주소 연결. @RequestMapping으로 인해 /board/list가 됨
	public void list(Model model, String keyword) {
		if(keyword == null)
			// keyword를 수정해준다.
			keyword = "";

		log.info("list로직 접속");
		// 전체 글 정보 얻어오기
		List<BoardVO> boardList = service.getList(keyword);
//		log.info(boardList);
		// view파일에 list라는 이름으로 넘겨주기
		model.addAttribute("list", boardList);
		model.addAttribute("keyword", keyword);
		
		// 1. views 하위에 경로에 맞는 폴더 및 .jsp 파일 생성
		// 2. 부트스트랩을 적용해 게시글 목록을 화면에 표시
	}
	*/
	
	// 페이징 처리가 되는 리스트 메서드를 연결
	// 페이징 처리용 메서드는 기존 접속주소는 같으나
	// 기존에 받던 자료에 더해서, Criterria를 추가로 더 입력받는다.
	@GetMapping("/list")
	public void list(SearchCriteria cri, Model model) {
		// pageNum, amount로 전달된 자료를 활용하여 게시물 목록 가져오기
		List<BoardVO> boardList = service.getListPaging(cri);
		
		// 페이지 밑에 깔아줄 페이징버튼 관련 정보 생성
		// 단순히 페이지버튼이 깔리는지 여부를 테스트할 때는
		// 우선 글 갯수를 정확히 모르므로 임시로 234개를 임의로 넣고
		// 페이징 버튼 개수는 최대 10개로 고정
		int total = service.getTotalBoardCnt(cri);
		
		PageDTO btnMaker = new PageDTO(cri, total, 10);
		
		model.addAttribute("cri", cri);
		model.addAttribute("list", boardList);
		model.addAttribute("btnMaker", btnMaker);
		
	}
	
	@GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<BoardAttachVO>> getAttachList(Long bno){
		return new ResponseEntity<List<BoardAttachVO>>(service.getAttachList(bno), HttpStatus.OK);
	}
	
	
	
	
	// 아래 주소로 데이터를 보내줄 수 있는 form을 작성
	// register.jsp 파일명으로 작성하여
	// @GetMapping으로 register.jsp에 접근할 수 있는
	// 컨트롤러 메서드 작성
	@PostMapping("/register")	// POST방식으로만 접속 허용
	public String register(BoardVO vo, RedirectAttributes rttr) {
		// 글을 썼으면 상세페이지나 글 목록으로 이동시켜야 한다.
		// 1. 글 쓰는 로직 실행
		service.register(vo);
		log.info(vo);
		// 2. list 주소로 강제 이동
		// 이동시킬 때 몇 번 글을 썻는지 안내해주는 로직을 추가한다.
		// addFlashAttribute()를 쓴다면
		// 일반 이동이 아닌 redirect이동시 데이터가 소실된다.
		// 이를 막기 위해 rttr.addFlashAttribute로 대체
		log.info(vo.getBno());
		rttr.addFlashAttribute("bno",vo.getBno());
		rttr.addFlashAttribute("result","register");
		
		log.info("====================================");
		log.info("register : " + vo);
		if(vo.getAttachList() != null)
			vo.getAttachList().forEach(attach -> log.info(attach));
		
		// views폴더 하위 board폴더의 list.jsp 출력
		// redirect로 이동시킬때는 "redirect:파일명"
		return "redirect:/board/list";
	}
	
	// GET방식으로만 접속되는 /board/register
	@GetMapping("/register")
	public void register() {
	}
	
	
	// 상세 페이지 조회는 Long bno에 적힌 글번호를 이용하여 출력
	// /get을 주소로 GetMapping을 사용하는 메서드 get을 작성
	// /get?파라미터명=글번호 형식으로 가져온다.(Get방식이므로)
	// service에서 get()을 호출해 가져온 글 하나의 정보를
	// get.jsp로 전송
	@GetMapping("/get")
	public String get(Long bno, Model model, SearchCriteria cri) {
		// 모든 로직 실행 전 bno가 null로 들어오는 경우
		if(bno == null)
			return "redirect:/board/list";
		// 글 번호만 전달받은 상황으로
		// 번호를이용해 전체 글 정보를 받아오는 것을 진행
		// bno번 글의 정보를 vo에 저장
		BoardVO vo = service.get(bno);
		
		// 조회 후 vo가 null일 경우
		if(vo == null)
			return "redirect:/board/list";
		
		// .jsp파일로 vo를 보냄
		model.addAttribute("vo", vo);
		
		return "/board/get";
	}
	
	
	// GET 방식으로 삭제를 허용하면 매크로 등을 이용해서
	// 마음대로 글삭제를 하는 경우가 생길수 있으므로
	// 무조건 삭제 버튼을 클릭해서 삭제할 수 있도록
	// POST 방식 접근만 허용
	// bno를 받아서 삭제하고, 삭제 후에는 "success"라는 문자열을
	// .jsp로 보내준다.
	// 삭제가 완료되면 redirect기능을 이용해 list페이지로 넘어가는 로직 작성
	@PostMapping("/remove")
	public String remove(Long bno, RedirectAttributes rttr) {
		
		service.remove(bno);
		rttr.addFlashAttribute("result", "remove");
		rttr.addFlashAttribute("bno", bno);
		
		return "redirect:/board/list";
	}
	
	
	// 수정로직은 POST방식으로 진행
	// /modify를 주소로 하여, 사용자가 수정할 수 있는 요소를
	// BoardVO로 받아서 수정하여 수정한 글의 디테일 페이지로 넘어온다.
	@PostMapping("/modify")
	// pageNum, searchType, keyword를 컨트롤러가 받아올 수 있도록
	// 해당 이름의 멤버변수를 가진 SearchCriteria를 파라미터로 선언
	public String modify(BoardVO vo, RedirectAttributes rttr, SearchCriteria cri) {
		// 상세 페이지는 bno가 파라미터로 주어져야 하기 때문에
		// 아래와 같이 리턴구문을 작성해야 한다.
		log.info("수정 로직 : " + vo);
		log.info("페이지번호 : " + cri.getPageNum());
		log.info("검색조건 : " + cri.getSearchType());
		log.info("키워드 : " + cri.getKeyword());
		service.modify(vo);
		
		// rttr.addAttribute("파라미터명", 전달자료)는 
		// 호출되면 redirect 주소 뒤에 파라미터를 붙여준다.
		// rttr.addFlashAttribute()는 넘어간 페이지에서 파라미터를
		// 쓸 수 있도록 전달하는 것으로 둘의 역할을 다르니 주의
		rttr.addAttribute("bno", vo.getBno());
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/board/";
	}
	
	// 글을 수정할 때는 modify.jsp를 이용해 수정을 진행
	// PostMapping을 이용해서 /boardmodify로 접속시 수정폼으로 접근
	// 수정 폼은 register.jsp와 비슷한 양식으로 작성되지만
	// 해당 글이 몇 번인지에 대한 정보도 화면에 출력하고
	// 글쓴이는 readonly를 걸어서 수정이 불가능해야 한다.
	// 수정 폼으로 접근하여 수정 폼에 내가 수정하고자 하는 글의 정보를 받은 후,
	// model.addAttribute로 정보를 .jsp로 보내서 폼을 채워둔다.
	@PostMapping("/boardmodify")
	public String modify(Long bno, Model model) {
		BoardVO vo = service.get(bno);
		model.addAttribute("vo", vo);
		
		return "/board/modify";
	}
}
