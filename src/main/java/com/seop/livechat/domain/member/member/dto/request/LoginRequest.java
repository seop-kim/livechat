package com.seop.livechat.domain.member.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "연락처는 필수 항목입니다.")
    private String phone;

    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    private String password;
}
