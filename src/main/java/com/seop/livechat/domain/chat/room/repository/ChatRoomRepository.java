package com.seop.livechat.domain.chat.room.repository;

import com.seop.livechat.domain.chat.room.dto.response.ChatRoomResponseDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ChatRoomRepository {
    private final Map<String, ChatRoomResponseDTO> chatRooms = new LinkedHashMap<>();

    public ChatRoomResponseDTO create(String name) {
        ChatRoomResponseDTO room = ChatRoomResponseDTO.create(name);
        chatRooms.put(room.getRoomId(), room);
        return room;
    }

    public List<ChatRoomResponseDTO> findAll() {
        List<ChatRoomResponseDTO> result = new ArrayList<>(chatRooms.values());
        Collections.reverse(result);
        return result;
    }

    public ChatRoomResponseDTO findById(String id) {
        return chatRooms.get(id);
    }
}