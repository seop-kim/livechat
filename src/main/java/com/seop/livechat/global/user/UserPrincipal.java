package com.seop.livechat.global.user;

import com.seop.livechat.domain.member.member.entity.Member;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@AllArgsConstructor
public class UserPrincipal implements UserDetails {
    private Member member;
    private Collection<? extends GrantedAuthority> authorities;
    private static final String ROLE = "ROLE_";

    public static UserPrincipal create(Member member) {
        GrantedAuthority authority = new SimpleGrantedAuthority(ROLE + member.getRole().getValue());
        List<GrantedAuthority> authorities = Collections.singletonList(authority);

        return UserPrincipal.builder()
                .member(member)
                .authorities(authorities)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getNickname();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}