package org.ict.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ict.domain.TestVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello Hello";
	}
	
	@RequestMapping("/sendVO")
	public TestVO sendTestVO() {
		
		TestVO testVO = new TestVO();
		testVO.setName("김철수");
		testVO.setAge(21);
		testVO.setMno(1);
		
		return testVO;
	}
	
	@RequestMapping("sendVOList")
	public List<TestVO> sendVOList(){
		
		List<TestVO> list = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			TestVO vo = new TestVO();
			vo.setMno(i);
			vo.setName(i + "철수");
			vo.setAge(20 + i);
			list.add(vo);
		}
		return list;
	}
	
	@RequestMapping("/sendVOMap")
	public Map<Integer, TestVO> sendVOMap() {

		Map<Integer, TestVO> map = new HashMap<Integer, TestVO>();

		for (int i = 0; i < 10; i++) {
			TestVO vo = new TestVO();
			vo.setMno(i);
			vo.setName(i + "철수");
			vo.setAge(20 + i);
			map.put(i, vo);
		}
		// map의 키값(왼쪽에 선언한 것)은 중복된 값이 들어올 수 없고
		// 들어온다면 가장 마지막에 넣은 하나만 남는다.
		System.out.println(map);
		return map;
	}
	
	
	@RequestMapping("/sendErrorAuth")
	public ResponseEntity<Void> sendListAuth(){
		// ResponseEntity는 생성자에 HttpStatus.코드번호를
		// 적어 해당 주소 접속시 어떤 접속코드를 사용자에게 넘겨줄 지 결정
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping("/sendErrorNot")
	public ResponseEntity<List<TestVO>> sendListNot() {
		
		List<TestVO> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			TestVO vo = new TestVO();
			vo.setMno(i);
			vo.setName(i + "철수");
			vo.setAge(20 + i);
			list.add(vo);
		}
		
		// ResponseEntity 생성자에, 파라미터 2개를 넘기면
		// 전송할 데이터와 전송 결과로 나오는 코드를 함께 넘길 수 있다.
		return new ResponseEntity<List<TestVO>>(list, HttpStatus.BAD_REQUEST);
	}
}
