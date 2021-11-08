package org.ict.chat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.log4j.Log4j;

@Log4j
public class HandlerChat extends TextWebSocketHandler{
	
	// {"room_id" : 방ID, "session" : 세션}을 연달아서 저장하는 혀
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	
	// 사용자가 신규 연결될 때마다 작성하는 메서드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		// 채팅방에 접속한 사용자 세션을 리스트에 저장
		sessionList.add(session);
		log.info(sessionList);
		
		// 모든 세션에 채팅 전달
		for (int i = 0; i < sessionList.size(); i++) {
			WebSocketSession s = sessionList.get(i);
			s.sendMessage(new TextMessage(session.getId() + "님이 입장했습니다."));
			
		}
	}
	
	// 한 유저가 메세지를 전달하면 같은 채팅방 전체 접속자에게 안내해주는 메서드
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		// 모든 세션에 채팅 전달
		for (int i = 0; i < sessionList.size(); i++) {
			WebSocketSession s = sessionList.get(i);
			s.sendMessage(new TextMessage(session.getId() + " : " + message.getPayload()));
			log.info(message);
		}
	}
	
	// 사용자가 접속 종료시 안내하는 메서드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
		// 채팅방에서 퇴장한 사용자 세션을 리스트에서 제거
		sessionList.remove(session);
		
		// 모든 세션에 채팅 전달
		for (int i = 0; i < sessionList.size(); i++) {
			WebSocketSession s = sessionList.get(i);
			s.sendMessage(new TextMessage(session.getId() + "님이 퇴장했습니다."));
		}
	}
}
