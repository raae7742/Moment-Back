package com.moment.CapturedMomentServer.service;

import com.moment.CapturedMomentServer.domain.User;
import com.moment.CapturedMomentServer.domain.UserRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/* 2021-08-08 김예진, 08-15 장현애 */
@Service
public interface UserService {

    //회원가입
    public User signUp(UserRequestDto requestDto, MultipartFile image);

    // 로그인할 이메일 & 패스워드 체크
    public User signIn(UserRequestDto.LoginDto userDto);

    //전체 회원 조회
    public List<User> findUsers();

    //email로 사람찾기
    public Optional<User> findOne(String email);

    //비밀번호 재설정하기(희망)

    // 프로필 수정하기
    public String updateProfile(String id, UserRequestDto.ProfileDto requestDto);

}
