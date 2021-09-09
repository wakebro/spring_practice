package org.ict.domain;

import lombok.Data;

// lombok을 이용하면 자동으로 getter, setter, toString을 생성
// 		좌측 Pcakage Explporer 생성한 클래스의 하단에서 확인
// @Data 어노테이션을 클래스 위에 붙이면 자동생성된다.
@Data
public class TestVO {
	private String name;
	private int age;
	private int lv;
}
