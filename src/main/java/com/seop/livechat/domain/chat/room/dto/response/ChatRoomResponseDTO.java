package com.seop.livechat.domain.chat.room.dto.response;

import com.seop.livechat.domain.chat.room.entity.ChatRoom;
import com.seop.livechat.domain.member.member.entity.Member;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

@Getter
@Builder
public class ChatRoomResponseDTO {
    private Long id;
    private Member member;
    private String name;
    private LocalDate createdAt;

    @Builder.Default
    private Set<WebSocketSession> sessions = new HashSet<>();

    public static ChatRoomResponseDTO create(ChatRoom room) {
        return ChatRoomResponseDTO.builder()
                .id(room.getId())
                .member(room.getMember())
                .name(room.getName())
                .createdAt(room.getCreateDate().toLocalDate())
                .build();
    }
}
