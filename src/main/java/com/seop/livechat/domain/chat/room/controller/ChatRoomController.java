package com.seop.livechat.domain.chat.room.controller;

import com.seop.livechat.domain.chat.room.repository.ChatRoomRepository;
import com.seop.livechat.global.user.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
public class ChatRoomController {
    private final ChatRoomRepository repository;

    @GetMapping(value = "/rooms")
    public String rooms(Model model) {
        model.addAttribute("list", repository.findAll());
        return "domain/chat/rooms";
    }

    @PostMapping(value = "/room")
    public String create(@RequestParam String name, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("roomName", repository.create(name));
        return "redirect:/chat/rooms";
    }

    @GetMapping("/room")
    public String getRoom(@AuthenticationPrincipal UserPrincipal user, String roomId, Model model) {
        log.info("roomId : " + roomId);
        model.addAttribute("room", repository.findById(roomId));
        model.addAttribute("username", user.getUsername());
        return "domain/chat/room";
    }
}
