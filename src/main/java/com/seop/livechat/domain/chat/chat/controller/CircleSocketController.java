package com.seop.livechat.domain.chat.chat.controller;

import com.seop.livechat.domain.chat.chat.dto.request.ChatMsgRequestDTO;
import com.seop.livechat.domain.chat.chat.dto.response.ChatMsgResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CircleSocketController {
    private final SimpMessagingTemplate template;
    private final static String SEND_URL = "/sub/chat/room/circle";
    private static Long circleSpeed = 0L;

    @MessageMapping(value = "/circle/in")
    public void in() {
        log.info("in circle game");
        template.convertAndSend(SEND_URL, circleSpeed);
    }

    @MessageMapping(value = "/circle/speed/up")
    public void up() {
        log.info("in speed up : cur-speed : " + circleSpeed);
        circleSpeed++;
        template.convertAndSend(SEND_URL, circleSpeed);
    }

    @MessageMapping(value = "/circle/speed/down")
    public void down() {
        log.info("in speed down : cur-speed : " + circleSpeed);
        circleSpeed--;
        template.convertAndSend(SEND_URL, circleSpeed);
    }
}
