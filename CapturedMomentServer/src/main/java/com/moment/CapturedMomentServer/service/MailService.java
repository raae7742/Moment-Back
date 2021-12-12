package com.moment.CapturedMomentServer.service;

import com.moment.CapturedMomentServer.domain.VerifyCode;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/* 2021-11-01 김예진*/
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final PasswordEncoder passwordEncoder;

    public boolean checkEmail(VerifyCode verifyCode){

        if(verifyCode.getCode() == null)
            return false;

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(verifyCode.getEmail());
        simpleMailMessage.setSubject("Captured-Moment 이메일 확인 코드");
        simpleMailMessage.setText("저희 서비스에 가입해 주셔서 감사합니다.\n"
        + "이메일 확인 코드는 " + verifyCode.getCode() + " 입니다.");

        javaMailSender.send(simpleMailMessage);
        return true;
    }

    public boolean changePassword(VerifyCode verifyCode){

        if(verifyCode.getCode() == null)
            return false;

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(verifyCode.getEmail());
        simpleMailMessage.setSubject("Captured-Moment 비밀번호 재설정 링크");
        ///##수정
       simpleMailMessage.setText("http://localhost:8080/user/password/" + verifyCode.getCode() + "\n"
       + "위 링크에서 새 비밀번호를 만드세요");

        javaMailSender.send(simpleMailMessage);
        return true;
    }
}
