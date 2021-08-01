package com.moment.CapturedMomentServer.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/* 회원 정보 Entity (현애, 2021-08-01) */

@Getter
@NoArgsConstructor
@Entity
public class User extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;                // 회원 id

    @Column(nullable = false)
    private String nickname;        // 회원 닉네임

    @Column(nullable = false)
    private String user_name;       // 회원 실명

    @Column(nullable = false)
    private String user_email;      // 회원 이메일

    @Column(nullable = false)
    private String user_pw;         // 회원 패스워드

    @Column(nullable = true)
    private String user_comment;    // 회원 자기소개 글

    @Column(nullable = true)
    private String user_profile;    // 회원 프로필 사진 URL

    public User(UserRequestDto requestDto) {
        this.nickname = requestDto.getNickname();
        this.user_name = requestDto.getUser_name();
        this.user_email = requestDto.getUser_email();
        this.user_pw = requestDto.getUser_pw();
        this.user_comment = requestDto.getUser_comment();
        this.user_profile = requestDto.getUser_profile();
    }
}
