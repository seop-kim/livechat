package com.seop.livechat.domain.chat.room.dto.response;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

@Getter
@Builder
public class ChatRoomResponseDTO {
    private String id;

    private String name;

    @Builder.Default
    private Set<WebSocketSession> sessions = new HashSet<>();

    public static ChatRoomResponseDTO create(String name) {
        return ChatRoomResponseDTO.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .build();
    }
}
