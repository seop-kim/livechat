package com.seop.livechat.domain.chat.chat.controller;

import com.seop.livechat.domain.chat.room.dto.request.ChatMsgRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StompChatController {
    private final SimpMessagingTemplate template;

    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMsgRequestDTO message) {
        message.setMessage("님이 채팅방에 참여하였습니다.");
        log.info(message.getWriter() + "님이 채팅방에 참여하였습니다.");
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatMsgRequestDTO message) {
        log.info(message.getRoomId() + " 방에 " + message.getWriter() + " 회원이 '" + message.getMessage()+"'를 보냈습니다.");
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}
