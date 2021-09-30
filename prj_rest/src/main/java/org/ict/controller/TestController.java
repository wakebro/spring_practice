package org.ict.controller;

import java.util.ArrayList;
import java.util.List;

import org.ict.domain.TestVO;
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
}
