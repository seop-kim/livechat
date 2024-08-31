package com.seop.livechat.domain.member.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberRole {
    MEMBER("MEMBER"),
    ;

    private final String value;
}