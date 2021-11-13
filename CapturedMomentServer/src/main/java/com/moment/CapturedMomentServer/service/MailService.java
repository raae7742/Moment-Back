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

    public String changePassword(String email){
        RandomString randomString = new RandomString(6);
        String code = randomString.nextString();

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Captured-Moment 이메일 확인 코드");
        simpleMailMessage.setText("임시 비밀번호는 " + code + " 입니다.\n"
        + "이 비밀번호로 로그인 해 주십시오.");

        javaMailSender.send(simpleMailMessage);
        return code;
    }
}
