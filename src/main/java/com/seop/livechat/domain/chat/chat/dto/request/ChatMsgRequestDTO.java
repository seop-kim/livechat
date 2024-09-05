package com.seop.livechat.domain.chat.chat.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMsgRequestDTO {
    private Long roomId;
    private String writer;
    private Long memberId;
    private String message;
}
