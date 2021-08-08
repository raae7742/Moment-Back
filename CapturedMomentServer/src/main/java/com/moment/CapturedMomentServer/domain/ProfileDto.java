package com.moment.CapturedMomentServer.domain;

import lombok.Getter;
import lombok.Setter;

/* 회원 프로필 정보를 전달할 때 사용할 임시 DTO (현애, 2021-08-01) */

@Getter
@Setter
public class ProfileDto {

    private Long id;            // 회원 id
    private String nickname;    // 회원 닉네임
    private String comment;     // 회원의 자기소개 글
    private String profile;     // 회원의 프로필 사진 URL

    public ProfileDto(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.comment = user.getComment();
        this.profile = user.getProfile();
    }
}