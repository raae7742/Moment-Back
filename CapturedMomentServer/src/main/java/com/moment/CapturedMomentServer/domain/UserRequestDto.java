package com.moment.CapturedMomentServer.domain;

import lombok.Getter;

/* 테스트용 회원 가입에 사용될 임시 UserRequestDto (현애, 2021-08-01) */

@Getter
public class UserRequestDto {
    private String nickname;
    private String user_name;
    private String user_email;
    private String user_pw;
    private String user_comment;
    private String user_profile;
}
