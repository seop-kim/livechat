package com.seop.livechat.domain.member.member.entity;

import com.seop.livechat.domain.chat.room.entity.ChatRoom;
import com.seop.livechat.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @NotNull
    @Column(unique = true)
    private String nickname;

    @NotNull
    private String password;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private MemberRole role;
}