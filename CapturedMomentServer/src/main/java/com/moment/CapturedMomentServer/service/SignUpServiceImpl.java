package com.moment.CapturedMomentServer.service;

import com.moment.CapturedMomentServer.domain.User;
import com.moment.CapturedMomentServer.domain.UserRequestDto;
import com.moment.CapturedMomentServer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/* 2021-08-08 김예진, 08-15 장현애 */

@Transactional
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;                              // 패스워드 인코더

    public User signUp(UserRequestDto requestDto){
        if (validateDuplicateMember(requestDto.getEmail())) return null;        // 이메일 중복 체크
        requestDto.setPw(passwordEncoder.encode(requestDto.getPw()));           // 비밀번호 암호화
        return userRepository.save(new User(requestDto));
    }

    public User loginCheck(UserRequestDto.LoginDto loginDto) {

        if (!validateDuplicateMember(loginDto.getEmail())) return null;         // 이메일 중복 체크
        User user = userRepository.findByEmail(loginDto.getEmail())             // 이메일로 user 검색
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL입니다."));

        if (!passwordEncoder.matches(loginDto.getPw(), user.getPw()))           // 패스워드 확인
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");

        return user;
    }

    //email 유효성 확인
    private Boolean validateDuplicateMember(String email){
        return userRepository.existsByEmail(email);
    }

    //전체 회원 조회
    public List<User> findUsers(){
        return userRepository.findAll();
    }

    //email로 사람찾기
    public Optional<User> findOne(String email){
        return userRepository.findByEmail(email);
    }

}
