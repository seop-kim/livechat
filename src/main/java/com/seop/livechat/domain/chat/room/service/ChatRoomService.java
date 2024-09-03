package com.seop.livechat.domain.chat.room.service;

import com.seop.livechat.domain.chat.room.dto.response.ChatRoomResponseDTO;
import com.seop.livechat.domain.chat.room.entity.ChatRoom;
import com.seop.livechat.domain.chat.room.repository.ChatRoomRepository;
import com.seop.livechat.domain.member.member.entity.Member;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public List<ChatRoomResponseDTO> getRooms() {
        List<ChatRoom> findAll = chatRoomRepository.findAll();

        return findAll.stream()
                .map(ChatRoomResponseDTO::create)
                .collect(Collectors.toList());
    }

    @Transactional
    public void addRoom(Member member, String name) {
        ChatRoom chatRoom = ChatRoom.create(member, name);
        chatRoomRepository.save(chatRoom);
    }

    public ChatRoomResponseDTO getRoom(Long roomId) {
        return ChatRoomResponseDTO.create(chatRoomRepository.findById(roomId).get());
    }
}
