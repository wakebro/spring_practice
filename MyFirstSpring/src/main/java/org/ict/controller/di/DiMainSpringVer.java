package org.ict.controller.di;

import org.ict.controller.di.classfile.*;
import org.springframework.context.support.GenericXmlApplicationContext;

public class DiMainSpringVer {
	public static void main(String[] args) {
		// 빈 컨테이너에 호출해 완성품 객체를 받아와 실행하는 코드 작성
		// 호출시 사용하는 연락용 객체는 GenericXmlApplicationContext
		GenericXmlApplicationContext context = new GenericXmlApplicationContext(
				"file:src/main/webapp/WEB-INF/spring/root-context.xml"
				);
		
		// 위에 root-context.xml이라는 bean-container와 연결한다고 지정했으니
		// 이후 그 공장에 있는 객체를 마음대로 꺼내 사용이 가능하다.
		// 얻어오는 방법은 위에서 생성한 context 객체를 이용해
		// context.getBean("bean이름", 자료형.class); 로 사용
//		Singer singer = context.getBean("singer", Singer.class);
//		singer.sing();
//		Broadcast broadcast = context.getBean("broadcast", Broadcast.class);
//		broadcast.broadcast();
		
		Satellite satellite = context.getBean("satellite", Satellite.class);
		satellite.satelliteBroadcast();
		
		// 호출이 끝난 후 context를 닫아준다.
		context.close();
	}
}
