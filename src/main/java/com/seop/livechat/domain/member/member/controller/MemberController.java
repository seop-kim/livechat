package com.seop.livechat.domain.member.member.controller;

import com.seop.livechat.domain.member.member.dto.request.JoinRequest;
import com.seop.livechat.domain.member.member.dto.request.LoginRequest;
import com.seop.livechat.domain.member.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/login")
    public String loginForm(LoginRequest loginRequest) {
        log.info("login form init");
        return "domain/member/member/login";
    }

    @GetMapping("/join")
    public String joinForm(JoinRequest joinRequest) {
        return "domain/member/member/join";
    }

    @PostMapping("/join")
    public String join(@Valid JoinRequest joinRequest) {
        memberService.join(joinRequest);
        return "redirect:/member/login";
    }
}
