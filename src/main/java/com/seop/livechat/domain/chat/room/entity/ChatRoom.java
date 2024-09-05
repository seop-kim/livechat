package com.seop.livechat.domain.chat.room.entity;

import com.seop.livechat.domain.chat.chat.entity.StompChat;
import com.seop.livechat.domain.member.member.entity.Member;
import com.seop.livechat.global.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom extends BaseEntity {
    private String name;

    @Comment("방장")
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Builder.Default
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<StompChat> chats = new ArrayList<>();

    public static ChatRoom create(Member member, String name) {
        return ChatRoom.builder()
                .member(member)
                .name(name)
                .build();
    }
}
