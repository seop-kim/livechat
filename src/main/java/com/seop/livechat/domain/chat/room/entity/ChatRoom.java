package com.seop.livechat.domain.chat.room.entity;

import com.seop.livechat.domain.member.member.entity.Member;
import com.seop.livechat.global.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom extends BaseEntity {
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public static ChatRoom create(Member member, String name) {
        return ChatRoom.builder()
                .member(member)
                .name(name)
                .build();
    }
}
