package org.ict.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// DTO는 Data Transfer Object로 데이터 전달 객체이다.
// DTO나 VO나 엄격하게 구분하는 것은 아니고, 둘 다 특정 데이터를
// 한 변수에 묶어서 보내기 위해 사용된다.
// 차이점은 VO는 DB에서 꺼낸 데이터를 바로 매칭시키고
// DTO는 DB에 있는 정보를 토대로 가공하여 사용된다.

@Getter
@Setter
@ToString
public class PageDTO {
	private int btnNum;
	private int startPage;
	private int endPage;
	private int total;
	private boolean prev, next;
	private SearchCriteria cri;

	// 위 변수 정보를 자동으로 계산하기 위한 생성자
	public PageDTO(SearchCriteria cri, int total, int btnNum) {
		this.cri = cri;
		this.total = total;
		this.btnNum = btnNum;

		// 위 저장된 멤버 면수를 이용해 나머지 정보 구하기

		// 끝 페이지 공식
		// 현재 보고있는 페이지를 (실수)btnNum으로 나눈 후
		// 다시 곱한 결과물에 올림을 하고 btnNum을 다시 곱해
		// 자릿수를 원상복구 시킨다.
		this.endPage = (int) Math.ceil((this.cri.getPageNum() / (double) this.btnNum)) * this.btnNum;
		
		// endPage를 토대로 startPage 구하기
		this.startPage = this.endPage - this.btnNum + 1;
		
		// 최종 페이지 구하기
		int realEnd = (int) Math.ceil(((this.total * 1.0) / cri.getAmount()));
		if (realEnd < endPage)
			endPage = realEnd;
		
		this.prev = this.startPage == 1 ? false : true;
		this.next = this.endPage < realEnd;

	}

}
