package org.ict.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;

// 빈 컨테이너 넣어주기(등록된 컨트롤만 실제로 작동)
@Controller
public class MvcController {

	// 기본주소(localhost:8181) 뒤에 /goA를 붙이면 goA()메서드 실행
	@RequestMapping(value = "/goA", method = RequestMethod.GET)
	// return 타입이 String인 경우 결과 페이지를 지정할 수 있음
	public String goA() {
		System.out.println("goA 주소 접속 감지");
		// 결과 페이지는 views 폴더 아래의 A.jsp
		return "01_A";
	}
	
	@RequestMapping(value = "/goB")
	public String goB() {
		System.out.println("goB 주소 접속 감지");
		return "02_B";
	}
	
	
	// 컨트롤러 인자 전달
	@RequestMapping(value = "/goC")
	// 주소 뒤 ?cNum=값 형태로 들어오는 값을 로직 내 cNum으로 처리
	// 들어온 파라미터를 .jsp파일로 전달하기 위해서는
	// Model model을 파라미터에 추가로 선언
	public String goC(Model model, int cNum) {
		System.out.println("주소로 전달받은 값 : " + cNum);
		
		// 전달받은 cNum을 C.jsp에 출력하는 로직
		model.addAttribute("cNum", cNum);
		
		return "03_C";
	}
	
	// 컨트롤러 인자전달_ requestParam을 이용해 변수명과 받는 이름이 일치하지 않게 하는 경우
	@RequestMapping(value="/goD")
	// @RequestParam("대채이름")은 변수 왼쪽에 선언
	// 이렇게 되면 적힌 변수명 대신 대체이름으로 치환해 받아온다.
	public String goD(@RequestParam("d") int dNum, Model model) {
		System.out.println("d 변수명으로 받은걸 dNum에 저장 : " + dNum);
		model.addAttribute("d",dNum);
		return "04_D";
	}
	
	// Quiz01
	// cToF 메서드
	// 섭씨 온도를 입력받아 화씨 온도로 바꾸어 출력하는 로직
	// (화씨 - 32) / 1.8 = 섭씨
	// 폼에서 post방식으로 제출했을 때에만 결과페이지로 넘어오도록 설정
	@RequestMapping(value = "/ctof", method = RequestMethod.POST)
	public String cToF(@RequestParam("cel") int cel, Model model) {
		double faren = (cel * 1.8) + 32;
		model.addAttribute("cel", cel);
		model.addAttribute("faren", faren);

		return "05_ctof";
	}
	// 폼으로 연결하는 메서드
	// 폼과 결과페이지가 같은 주소를 공유하게 하기 위해서 폼쪽을 GET방식 접근 허용
//	@RequestMapping(value="/ctofform")
	@RequestMapping(value="/ctof", method = RequestMethod.GET)
	public String cToTForm() {
		// ctofform을 이용해 섭씨온도를 입력하고 제출 버튼을 누르면
		// 결과값이 나오는 로직을 작성
		// input 태그의 name속성은 cel로 주고
		// action은 value로 적힌 주소값으로 설정
		return "05_ctofform";
	}
	
	
	// Quiz 02
	// BMI측정 페이지_폼/결과 페이지로 구성
	// BMI = 체중 / (키(m) ^2)
	@RequestMapping(value="/bmi", method=RequestMethod.GET)
	public String bmiForm() {
		// 폼 페이지 이동
		return "06_bmiform";
	}
	@RequestMapping(value="/bmi", method=RequestMethod.POST)
	public String bmi(@RequestParam("height") int h_,
					  @RequestParam("weight") int w, 
					  Model model) {
		// 결과 페이지
		double h = (double) h_ / 100;
		int result = (int) (w / (h * h));
		model.addAttribute("result", result);
		return "06_bmi";
	}
	
	
	
	// @PathVariable 사용
	// PathVariable을 이용하면 url 패턴만으로도 특정 파라미터를 받아올 수 있다.
	// rest방식으로 url을 처리할 때 주로 사용하는 방식
	// /pathtest/숫자 중 숫자 위치에 온 것은 page라는 변수값으로 간주
	@RequestMapping(value="/pathtest/{page}")
	// int page 왼쪽에 @PathVariable을 붙여서 처리해야 연동됨
	public String pathTest(@PathVariable int page, Model model) {
		// 받아온 page 변수를 path.jsp로 전송
		// path.jsp에는 ${path} 페이지 조회 중 입니다 라는 문장이 뜨도록 설정
		model.addAttribute("page", page);
		
		
		return "07_path";
		
	}
	
}
