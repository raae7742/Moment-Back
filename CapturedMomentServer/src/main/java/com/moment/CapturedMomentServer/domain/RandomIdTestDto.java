package com.moment.CapturedMomentServer.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RandomIdTestDto {

    @NotNull
    private String name;       // 회원 실명

    @NotNull
    private String email;      // 회원 이메일
}
