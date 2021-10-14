package com.moment.CapturedMomentServer.domain.kakao;

import lombok.Getter;

// 카카오 로그인 액세스 토큰
@Getter
public class OAuthToken {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;
    private int refresh_token_expires_in;
}
