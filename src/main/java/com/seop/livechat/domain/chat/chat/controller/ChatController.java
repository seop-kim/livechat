package com.seop.livechat.domain.chat.chat.controller;

import com.seop.livechat.global.user.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ChatController {
    @GetMapping("/chat")
    public String chat(@AuthenticationPrincipal UserPrincipal user, Model model) {
        model.addAttribute("username", user.getUsername());
        return "domain/chat/chat";
    }
}
