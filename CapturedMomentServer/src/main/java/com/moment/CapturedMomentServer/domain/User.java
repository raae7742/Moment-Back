package com.moment.CapturedMomentServer.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/* 회원 정보 Entity (현애, 2021-08-01 예진, 2021-08-08) */

@Getter
@NoArgsConstructor
@Entity
public class User extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;                // 회원 id

    @NotNull
    @Column(length = 50)
    private String nickname;        // 회원 닉네임

    @NotNull
    @Column(length = 20)
    private String name;       // 회원 실명

    @NotNull
    @Column(length = 20)
    private String email;      // 회원 이메일

    @NotNull
    @Column(length = 20)
    private String pw;         // 회원 패스워드

    @Column(length = 100)
    private String comment;    // 회원 자기소개 글

    private String img_url;    // 회원 프로필 사진 URL

    public User(UserRequestDto userDto){
        this.nickname = userDto.getNickname();
        this.name = userDto.getName();
        this.email = userDto.getEmail();
        this.pw = userDto.getPw();
        this.comment = userDto.getComment();
        this.img_url = userDto.getImg_url();
    }

}
