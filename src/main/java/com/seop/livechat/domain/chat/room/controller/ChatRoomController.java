package com.seop.livechat.domain.chat.room.controller;

import com.seop.livechat.domain.chat.room.dto.response.ChatRoomResponseDTO;
import com.seop.livechat.domain.chat.room.service.ChatRoomService;
import com.seop.livechat.global.user.UserPrincipal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/room")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @GetMapping
    public String rooms(Model model) {
        List<ChatRoomResponseDTO> response = chatRoomService.getRooms();
        model.addAttribute("list", response);
        return "domain/chat/rooms";
    }

    @PostMapping
    public String create(@AuthenticationPrincipal UserPrincipal user, @RequestParam String name) {
        chatRoomService.addRoom(user.getMember(), name);
        return "redirect:/room";
    }

    @GetMapping("{id}")
    public String getRoom(@AuthenticationPrincipal UserPrincipal user,
                          @PathVariable("id") Long id,
                          Model model) {
        log.info("roomId : " + id);
        model.addAttribute("room", chatRoomService.getRoom(id));
        model.addAttribute("member", user.getMember());
        return "domain/chat/room";
    }
}
