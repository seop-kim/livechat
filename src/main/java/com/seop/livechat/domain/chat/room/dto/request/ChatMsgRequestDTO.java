package com.seop.livechat.domain.chat.room.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMsgRequestDTO {
    private String roomId;
    private String writer;
    private String message;
}
