package com.moment.CapturedMomentServer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* 회원 정보 Entity (현애, 2021-08-01 예진, 2021-08-08) */

@Getter
@NoArgsConstructor
@Entity
@Table(name = "user")
@Builder
@AllArgsConstructor
public class User extends Timestamped implements UserDetails {  // SpringSecurity 사용을 위해 UserDetails 상속

    @JsonIgnore
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;                // 회원 id

    @Column(name = "nickname")
    private String nickname;        // 회원 닉네임

    @Column(name = "name")
    private String name;       // 회원 실명

    @Column(name = "email", unique = true)
    private String email;      // 회원 이메일

    @JsonIgnore
    @Column(name = "pw")
    private String pw;         // 회원 패스워드

    @Column(name = "comment", nullable = true)
    private String comment;    // 회원 자기소개 글

    @Column(name = "img_url", nullable = true)
    private String img_url;    // 회원 프로필 사진 URL

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();     // 유저의 접근 권한(Admin, User)

    public User(UserRequestDto requestDto) {
        this.nickname = requestDto.getNickname();
        this.name = requestDto.getName();
        this.email = requestDto.getEmail();
        this.pw = requestDto.getPw();
        this.comment = requestDto.getComment();
        this.img_url = requestDto.getImg_url();
    }

    /* 아래부터 UserDetails Override 메소드 */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return pw;
    }

    @Override
    public String getUsername() {
        return email;
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
