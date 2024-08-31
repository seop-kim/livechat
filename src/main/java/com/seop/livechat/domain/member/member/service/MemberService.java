package com.seop.livechat.domain.member.member.service;

import com.seop.livechat.domain.member.member.dto.request.JoinRequest;
import com.seop.livechat.domain.member.member.entity.Member;
import com.seop.livechat.domain.member.member.entity.MemberRole;
import com.seop.livechat.domain.member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void join(JoinRequest joinRequest) {
        Member member = Member.builder()
                .nickname(joinRequest.getNickname())
                .password(passwordEncoder.encode(joinRequest.getPassword()))
                .role(MemberRole.MEMBER)
                .build();

        memberRepository.save(member);
    }

    public Member findById(long id) {
        return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no member"));
    }
}