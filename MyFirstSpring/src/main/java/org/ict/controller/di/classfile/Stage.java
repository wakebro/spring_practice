package org.ict.controller.di.classfile;

public class Stage {
	private Singer singer;
	
	// 객체 생성시 무조건 Singer타입을 파라미터로 전달
	public Stage(Singer singer) {
		this.singer = singer;
	}
	
	public void perform() {
		System.out.print("무대에서 ");
		// 실제 공연은 가수가 담당.
		singer.sing();
	}
}
