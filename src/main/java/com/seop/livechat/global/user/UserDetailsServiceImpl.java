package com.seop.livechat.global.user;

import com.seop.livechat.domain.member.member.entity.Member;
import com.seop.livechat.domain.member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        Member member = memberRepository.findByNickname(phoneNumber)
                .orElseThrow(() -> new UsernameNotFoundException("phoneNumber doesn't exist"));
        return UserPrincipal.create(member);
    }
}