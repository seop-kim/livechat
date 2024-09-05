package com.seop.livechat.domain.chat.chat.handler;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Component
public class ChatHandler extends TextWebSocketHandler {
    private final static List<WebSocketSession> CHAT_LIST = new ArrayList<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("session.getPrincipal().getName() [ " + session.getPrincipal().getName() + " ]");
        log.info("msg [ " + session.getPrincipal().getName() + payload + " ]");

        // 메시지에 username을 추가
        String messageWithUsername = session.getPrincipal().getName() + ": " + payload;

        // 모든 세션에 수정된 메시지 전송
        for (WebSocketSession sess : CHAT_LIST) {
            sess.sendMessage(new TextMessage(messageWithUsername));
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        CHAT_LIST.add(session);
        log.info(session.getPrincipal().getName() + " client in");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        CHAT_LIST.remove(session);
        log.info(session.getPrincipal().getName() + " client out");
    }
}
