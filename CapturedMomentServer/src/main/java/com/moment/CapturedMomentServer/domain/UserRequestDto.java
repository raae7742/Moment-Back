package com.moment.CapturedMomentServer.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

/* UserRequestDto (현애, 2021-08-15 수정) */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    private String nickname;   // 회원 닉네임
    private String name;       // 회원 실명
    private String email;      // 회원 이메일
    private String pw;         // 회원 패스워드
    private String comment;    // 회원 자기소개 글
    private List<String> roles = new ArrayList<>();
    private String img_url;    // 회원 프로필 사진 URL

    @Getter
    @Setter
    public static class LoginDto {
        private String email;
        private String pw;
    }
}
