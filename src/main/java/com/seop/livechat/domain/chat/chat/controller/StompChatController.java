package com.seop.livechat.domain.chat.chat.controller;

import com.seop.livechat.domain.chat.chat.dto.response.ChatMsgResponseDTO;
import com.seop.livechat.domain.chat.chat.service.StompChatService;
import com.seop.livechat.domain.chat.chat.dto.request.ChatMsgRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StompChatController {
    private final SimpMessagingTemplate template;
    private final StompChatService stompChatService;

    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMsgRequestDTO message) {
        message.setMessage("님이 채팅방에 참여하였습니다.");
        log.info(message.getWriter() + "님이 채팅방에 참여하였습니다.");
        // TODO
        //  DB
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatMsgRequestDTO request) {
        String sendUri = "/sub/chat/room/" + request.getRoomId();

        log.info(request.getRoomId() + "번방에 " + request.getWriter() + " 회원이 '" + request.getMessage() + "'를 보냈습니다.");
        template.convertAndSend(sendUri, ChatMsgResponseDTO.of(request));

        // 비동기 처리
        asyncAddChat(request, sendUri);
    }

    @Async
    protected void asyncAddChat(ChatMsgRequestDTO request, String sendUri) {
        stompChatService.addChat(request);
        ChatMsgResponseDTO response = ChatMsgResponseDTO.create("[공지] '" + request.getWriter() + "'님의 메시지를 DB에 저장하였습니다.");
        template.convertAndSend(sendUri, response);
    }
}
