package com.seop.livechat.domain.chat.chat.dto.response;

import com.seop.livechat.domain.chat.chat.dto.request.ChatMsgRequestDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatMsgResponseDTO {
    private String writer;
    private String message;

    public static ChatMsgResponseDTO of(ChatMsgRequestDTO request) {
        return ChatMsgResponseDTO.builder()
                .writer(request.getWriter())
                .message(" :" + request.getMessage())
                .build();
    }

    public static ChatMsgResponseDTO create(String noticeMsg) {
        return ChatMsgResponseDTO.builder()
                .writer("ADMIN")
                .message(noticeMsg)
                .build();
    }
}
