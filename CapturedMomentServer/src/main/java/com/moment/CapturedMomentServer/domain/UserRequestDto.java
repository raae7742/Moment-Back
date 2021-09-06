package com.moment.CapturedMomentServer.domain;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

/* UserRequestDto (현애, 2021-08-15 수정) */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @ApiParam(value = "회원 닉네임", required = true)
    @ApiModelProperty(example = "닉네임")
    @NotNull
    private String nickname;   // 회원 닉네임

    @ApiParam(value = "회원 이름", required = true)
    @ApiModelProperty(example = "이름")
    @NotNull
    private String name;       // 회원 실명

    @ApiParam(value = "회원 이메일", required = true)
    @ApiModelProperty(example = "이메일")
    @NotNull
    private String email;      // 회원 이메일

    @ApiParam(value = "회원 패스워드", required = true)
    @ApiModelProperty(example = "패스워드")
    @NotNull
    private String pw;         // 회원 패스워드

    @ApiParam(value = "회원 자기소개")
    @ApiModelProperty(example = "자기소개")
    private String comment;    // 회원 자기소개 글

    @ApiModelProperty(example = "입력하지 마세요(권한 설정)")
    private List<String> roles = new ArrayList<>();

    @ApiParam(value = "회원 프로필 이미지 URL")
    @ApiModelProperty(example = "프로필 이미지 URL")
    private String img_url;    // 회원 프로필 사진 URL


    // 로그인 dto
    @Getter
    @Setter
    public static class LoginDto {
        @ApiParam(value = "회원 이메일", required = true)
        @ApiModelProperty(example = "이메일")
        private String email;

        @ApiParam(value = "회원 패스워드", required = true)
        @ApiModelProperty(example = "패스워드")
        private String pw;
    }

    // 마이페이지 프로필 dto
    @Getter
    @Setter
    public static class ProfileDto {
        @ApiParam(value = "회원 닉네임", required = true)
        @ApiModelProperty(example = "닉네임")
        private String nickname;    // 회원 닉네임

        @ApiParam(value = "회원 자기소개")
        @ApiModelProperty(example = "자기소개 글")
        private String comment;     // 회원의 자기소개 글

        @ApiParam(value = "회원 프로필 이미지 URL")
        @ApiModelProperty(example = "프로필 이미지 URL")
        private String profile;     // 회원의 프로필 사진 URL

        public ProfileDto(User user) {
            this.nickname = user.getNickname();
            this.comment = user.getComment();
            this.profile = user.getImg_url();
        }
    }
}
