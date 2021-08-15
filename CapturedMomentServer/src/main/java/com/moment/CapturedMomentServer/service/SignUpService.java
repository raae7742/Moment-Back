package com.moment.CapturedMomentServer.service;

import com.moment.CapturedMomentServer.domain.User;
import com.moment.CapturedMomentServer.domain.UserRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/* 2021-08-08 김예진, 08-15 장현애 */
@Service
public interface SignUpService {

    //회원가입
    public User signUp(UserRequestDto requestDto);

    // 로그인할 이메일 & 패스워드 체크
    public User loginCheck(UserRequestDto.LoginDto userDto);

    //전체 회원 조회
    public List<User> findUsers();

    //email로 사람찾기
    public Optional<User> findOne(String email);

}
