package com.seop.livechat.domain.chat.chat.service;

import com.seop.livechat.domain.chat.chat.entity.StompChat;
import com.seop.livechat.domain.chat.chat.repository.StompChatRepository;
import com.seop.livechat.domain.chat.chat.dto.request.ChatMsgRequestDTO;
import com.seop.livechat.domain.chat.room.entity.ChatRoom;
import com.seop.livechat.domain.chat.room.repository.ChatRoomRepository;
import com.seop.livechat.domain.member.member.entity.Member;
import com.seop.livechat.domain.member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StompChatService {
    private final StompChatRepository stompChatRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void addChat(ChatMsgRequestDTO req) {
        ChatRoom findChatRoom = chatRoomRepository.findById(req.getRoomId()).get();
        Member findMember = memberRepository.findById(req.getMemberId()).get();

        log.info("add chat : [" + findChatRoom.getId() + "] " + findChatRoom.getName() + " :: " + findMember.getNickname() + " : " + req.getMessage());
        stompChatRepository.save(StompChat.create(findChatRoom, findMember, req.getMessage()));
    }
}
