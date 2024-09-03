package com.seop.livechat.domain.chat.session.controller;

import com.seop.livechat.domain.chat.session.dto.SessionStatusDTO;
import com.seop.livechat.global.user.UserPrincipal;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    @GetMapping("/checkSession")
    @ResponseBody
    public ResponseEntity<SessionStatusDTO> checkSession(HttpSession session, @AuthenticationPrincipal UserPrincipal user) {
        boolean active = user != null && session != null && session.getAttribute("SPRING_SECURITY_CONTEXT") != null;
        SessionStatusDTO response = SessionStatusDTO.builder().active(active).build();
        return ResponseEntity.ok(response);
    }
}