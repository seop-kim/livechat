package com.seop.livechat.domain.chat.chat.entity;

import com.seop.livechat.domain.chat.room.entity.ChatRoom;
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
public class StompChat extends BaseEntity {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private ChatRoom room;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private String msg;

    public static StompChat create(ChatRoom room, Member member, String msg) {
        return StompChat.builder()
                .room(room)
                .member(member)
                .msg(msg)
                .build();
    }
}
