package org.ict.controller.di.classfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Satellite {
	
	@Autowired
	private Broadcast broadcast;
	
	public Satellite(Broadcast broadcast) {
		this.broadcast = broadcast;
	}
	
	public void satelliteBroadcast() {
		System.out.print("위성 ");
		broadcast.broadcast();
	}
}
