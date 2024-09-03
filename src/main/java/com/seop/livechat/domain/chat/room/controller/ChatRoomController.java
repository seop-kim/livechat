package com.seop.livechat.domain.chat.room.controller;

import com.seop.livechat.domain.chat.room.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
public class ChatRoomController {
    private final ChatRoomRepository repository;

    //채팅방 목록 조회
    @GetMapping(value = "/rooms")
    public ModelAndView rooms() {
        log.info("# All Chat Rooms");
        ModelAndView mv = new ModelAndView("domain/chat/rooms");
        mv.addObject("list", repository.findAll());
        return mv;
    }

    //채팅방 개설
    @PostMapping(value = "/room")
    public String create(@RequestParam String name, RedirectAttributes redirectAttributes) {
        log.info("# Create Chat Room , name: " + name);
        redirectAttributes.addFlashAttribute("roomName", repository.create(name));
        return "redirect:/chat/rooms";
    }

    //채팅방 조회
    @GetMapping("/room")
    public void getRoom(String roomId, Model model) {
        log.info("# get Chat Room, roomID : " + roomId);
        model.addAttribute("room", repository.findById(roomId));
    }
}
