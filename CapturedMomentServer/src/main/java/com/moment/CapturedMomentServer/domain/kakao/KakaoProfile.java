package com.moment.CapturedMomentServer.domain.kakao;

import lombok.Getter;
import org.springframework.context.annotation.Profile;

import java.util.Properties;

// 카카오 로그인 계정 정보
@Getter
public class KakaoProfile {
    private int id;
    private String connected_at;
    private KakaoAccount kakao_account;

    @Getter
    public static class KakaoAccount {
        private Boolean has_email;
        private Boolean email_needs_agreement;
        private Boolean is_email_valid;
        private Boolean is_email_verified;
        private String email;
    }
}
