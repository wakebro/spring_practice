package org.ict.controller.di;

import org.ict.controller.di.classfile.*;

public class DiMainJavaVer {

	public static void main(String[] args) {
		// 1. 가수 객체를 생성하여 기능을 호출
		Singer s1 = new  Singer();
		s1.sing();
		
		// 2. 무대 객체를 생성하여 기능을 호출
		// 무대 객체는 생성시 반드시 singer가 먼저 있어야 하므로
		// 무대는 singer에 의존한다.
		Stage stage = new Stage(s1);
		
		// 3. Broadcast객체를 생성하여 기능을 호출
		// Broadcast 객체는 Stage를 주입하여 메서드 호출
		Broadcast broadcast = new Broadcast(stage);
		broadcast.broadcast();
	}
}
