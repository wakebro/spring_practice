package org.ict.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	@Select("SELECT SYSDATE FROM DUAL")
	public String getTime();
	
	// 기본적으로 복잡한 쿼리문 작성을하려면 xml파일과 인터페이스를 연동한다.
	// 먼저, 인터페이스에는 아래와 같이 메서드를 선언만 한다.
	// 이후 같은 패키지 내부에 xml을 생성하여 실제 getTime2가 호출되면
	// 실행할 실행문을 작성한다.
	public String getTime2();
	
	// challenge
	public String getTime3();
}
