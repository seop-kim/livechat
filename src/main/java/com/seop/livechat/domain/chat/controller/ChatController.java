package com.seop.livechat.domain.chat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ChatController {
    @GetMapping("/chat")
    public String chatGET(){

        log.info("@ChatController, chat GET()");

        return "domain/chat/chat";
    }
}
