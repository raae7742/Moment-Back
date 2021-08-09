package com.moment.CapturedMomentServer.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

/* 테스트용 회원 가입에 사용될 임시 UserRequestDto (현애, 2021-08-01) */
/*예진 2021-08-08 수정 그냥 써도 될듯*/

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    private String nickname;        // 회원 닉네임

    private String name;       // 회원 실명

    private String email;      // 회원 이메일

    private String pw;         // 회원 패스워드

    private String comment;    // 회원 자기소개 글

    private String img_url;    // 회원 프로필 사진 URL
}
