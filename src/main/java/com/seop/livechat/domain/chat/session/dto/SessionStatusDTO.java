package com.seop.livechat.domain.chat.session.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SessionStatusDTO {
    private boolean active;

    public void updateActive(boolean active) {
        this.active = active;
    }
}
